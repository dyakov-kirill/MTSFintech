package ru.mtsbank.repositories;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Repository;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.animals.AnimalType;
import ru.mtsbank.services.CreateAnimalService;

import java.time.LocalDate;
import java.util.*;

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
        for (List<Animal> animalsList: animals.values()) {
            for (Animal animal : animalsList) {
                if (animal.getBirthDate().isLeapYear()) {
                    res.put(animal.getAnimalType().toString() + " " + animal.getName(), animal.getBirthDate());
                }
            }

        }
        return res;
    }

    @Override
    public Map<Animal, Integer> findOlderAnimal(int N) {
        Map<Animal, Integer> res = new HashMap<>();
        if (animals == null) {
            return res;
        }
        Animal oldestAnimal = null;
        int thisYear = LocalDate.now().getYear();
        for (List<Animal> animalsList: animals.values()) {
            for (Animal animal : animalsList) {
                int age = getAnimalAge(animal);
                if (age > N) {
                    res.put(animal, age);
                }
                if (oldestAnimal == null || age > getAnimalAge(oldestAnimal)) {
                    oldestAnimal = animal;
                }
            }
        }
        if (res.isEmpty() && oldestAnimal != null) {
            res.put(oldestAnimal, getAnimalAge(oldestAnimal));
        }
        return res;
    }

    @Override
    public Map<String, Integer> findDuplicate() {
        ArrayList<Animal> appearedAnimals = new ArrayList<>();
        Map<String, Integer> duplicates = new HashMap<>();
        if (animals == null) {
            return duplicates;
        }
        for (List<Animal> animals: animals.values()) {
            for (Animal animal : animals) {
                if (!appearedAnimals.contains(animal)) {
                    appearedAnimals.add(animal);
                } else {
                    String key = animal.getAnimalType().toString();
                    duplicates.put(key, duplicates.getOrDefault(key, 0) + 1);
                }
            }
        }
        return duplicates;
    }

    @Override
    public void printDuplicate() {
        Map<String, Integer> duplicates = findDuplicate();
        if (duplicates.keySet().isEmpty()) {
            System.out.println("Duplicates not found");
        } else {
            System.out.print("Duplicates founded:");
            for (String key: duplicates.keySet()) {
                System.out.println(key + " = " + duplicates.get(key));
            }
        }
    }

    public void setAnimals(Map<String, List<Animal>> animals) {
        this.animals = animals;
    }

    private int getAnimalAge(Animal animal) {
        int thisYear = LocalDate.now().getYear();
        int age = thisYear - animal.getBirthDate().getYear();
        if (LocalDate.now().getDayOfYear() < animal.getBirthDate().getDayOfYear()) {
            age--;
        }
        return age;
    }
}
