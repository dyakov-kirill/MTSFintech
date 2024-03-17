package ru.mtsbank.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.mtsbank.entity.Animal;
import ru.mtsbank.entity.AnimalType;
import ru.mtsbank.services.CreateAnimalService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

    private Map<String, List<Animal>> animals;

    private final CreateAnimalService animalService;

    public AnimalRepositoryImpl(CreateAnimalService animalService) {
        this.animalService = animalService;
    }

    @PostConstruct
    private void postConstruct() {
        animals = animalService.createAnimals(AnimalType.CAT);
    }

    @Override
    public Map<String, LocalDate> findLeapYearNames() {
        Map<String, LocalDate> res = new HashMap<>();
        if (animals == null) {
            return res;
        }
        animals.values().stream().flatMap(List::stream)
                .filter(e -> e.getBirthDate().isLeapYear())
                .forEach(e -> res.put(e.getAnimalType().toString() + " " + e.getName(), e.getBirthDate()));
       return res;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) {
        Map<Animal, Integer> res = new HashMap<>();
        if (animals == null) {
            return res;
        }
        animals.values().stream().flatMap(List::stream)
                .filter(e -> getAnimalAge(e) > N)
                .forEach(e -> res.put(e, getAnimalAge(e)));
        if (res.isEmpty()) {
            Animal oldestAnimal = animals.values().stream()
                    .flatMap(List::stream)
                    .max(Comparator.comparing(Animal::getCost))
                    .orElse(null);
            res.put(oldestAnimal, getAnimalAge(oldestAnimal));
        }
        return res;
    }

    @Override
    public Map<String, List<Animal>> findDuplicate() {
        ArrayList<Animal> appearedAnimals = new ArrayList<>();
        Map<String, List<Animal>> duplicates = new HashMap<>();
        if (animals == null) {
            return duplicates;
        }
        animals.values().stream()
                .flatMap(List::stream)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet().stream()
                .filter(e -> e.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted(Comparator.comparing(Animal::getName))
                .forEach(e -> {
                    List<Animal> list = duplicates.getOrDefault(e.getAnimalType().toString(), new ArrayList<>());
                    list.add(e);
                    duplicates.put(e.getAnimalType().toString(), list);
                });
        return duplicates;
    }

    @Override
    public void printDuplicate() {
        Map<String, List<Animal>> duplicates = findDuplicate();
        if (duplicates.keySet().isEmpty()) {
            System.out.println("Duplicates not found");
        } else {
            System.out.print("Duplicates founded:");
            duplicates.forEach((key, value) -> value.forEach(System.out::println));
        }
    }

    @Override
    public void findAverageAge(List<Animal> animals) {
        if (animals == null) {
            System.out.println(0);
            return;
        }
        animals.stream().mapToInt(this::getAnimalAge).average().ifPresent(System.out::println);
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animals) {
        if (animals == null) {
            return Collections.emptyList();
        }
        return animals.stream()
                .filter(e -> getAnimalAge(e) > 5 && e.getCost().compareTo(calculateAverageCost(animals)) >= 0)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinCostAnimals(List<Animal> animals) {
        if (animals == null) {
            return Collections.emptyList();
        }
        return animals.stream()
                .filter(e -> e.getCost().equals(animals.stream()
                        .map(Animal::getCost).min(BigDecimal::compareTo).orElse(BigDecimal.valueOf(0))))
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    private BigDecimal calculateAverageCost(List<Animal> animals) {
        BigDecimal sum = animals.stream().map(Animal::getCost)
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(animals.size()), RoundingMode.CEILING);
    }

    public void setAnimals(Map<String, List<Animal>> animals) {
        this.animals = animals;
    }

    private int getAnimalAge(Animal animal) {
        if (animal == null) {
            return 0;
        }
        int thisYear = LocalDate.now().getYear();
        int age = thisYear - animal.getBirthDate().getYear();
        if (LocalDate.now().getDayOfYear() < animal.getBirthDate().getDayOfYear()) {
            age--;
        }
        return age;
    }
}
