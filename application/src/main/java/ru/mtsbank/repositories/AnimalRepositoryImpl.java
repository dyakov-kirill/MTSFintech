package ru.mtsbank.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.mtsbank.entity.Animal;
import ru.mtsbank.entity.AnimalType;
import ru.mtsbank.entity.Cat;
import ru.mtsbank.repositories.exceptions.NegativeArgumentException;
import ru.mtsbank.repositories.exceptions.NullPointerArgumentException;
import ru.mtsbank.repositories.exceptions.WrongListArgumentSize;
import ru.mtsbank.services.CreateAnimalService;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {


    private ConcurrentMap<String, List<Animal>> animals;

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
        if (N < 0) {
            throw new NegativeArgumentException("Argument " + N + " are not allowed");
        }
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
                    .min(Comparator.comparing(Animal::getBirthDate))
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
            System.out.print("Duplicates founded: ");
            duplicates.forEach((key, value) -> value.forEach(animal -> System.out.println(animal.getName())));
        }
    }

    @Override
    public void findAverageAge(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerArgumentException("Provided null-pointer argument in findAverageAge method");
        }
        animals.stream().mapToInt(this::getAnimalAge).average().ifPresent(value -> System.out.println("Средний возраст: " + value));
    }

    @Override
    public List<Animal> findOldAndExpensive(List<Animal> animals) {
        if (animals == null) {
            throw new NullPointerArgumentException("Provided null-pointer argument in findOldAndExpensive method");
        }
        return animals.stream()
                .filter(e -> getAnimalAge(e) > 5 && e.getCost().compareTo(calculateAverageCost(animals)) >= 0)
                .sorted(Comparator.comparing(Animal::getBirthDate))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findMinCostAnimals(List<Animal> animals) throws WrongListArgumentSize {
        if (animals == null) {
            throw new NullPointerArgumentException("Provided null-pointer argument in findMinCostAnimals method");
        } else if (animals.isEmpty()) {
            throw new WrongListArgumentSize("Provided empty list in findMinCostArgument");
        }
        return animals.stream()
                .filter(e -> e.getCost().equals(animals.stream()
                        .map(Animal::getCost).min(BigDecimal::compareTo).orElse(BigDecimal.valueOf(0))))
                .map(Animal::getName)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    @Override
    public Map<String, List<Animal>> getAnimals() {
        return animals;
    }

    @Override
    public void addDuplicates(int n) {
        Animal cat = new Cat("DuplicatedCat", "1", BigDecimal.ONE, "1", LocalDate.now());
        List<Animal> list = animals.getOrDefault("CAT", new ArrayList<Animal>());
        for (int i = 0; i < n; i++) {
            list.add(new Cat(cat.getName(), cat.getBreed(), cat.getCost(), cat.getChar(), cat.getBirthDate()));
        }
        animals.put("CAT", list);
    }

    private BigDecimal calculateAverageCost(List<Animal> animals) {
        BigDecimal sum = animals.stream().map(Animal::getCost)
                .map(Objects::requireNonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return sum.divide(new BigDecimal(animals.size()), RoundingMode.CEILING);
    }

    public void setAnimals(ConcurrentMap<String, List<Animal>> animals) {
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
