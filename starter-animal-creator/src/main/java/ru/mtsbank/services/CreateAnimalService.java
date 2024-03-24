package ru.mtsbank.services;

import ru.mtsbank.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public interface CreateAnimalService {

    default ConcurrentMap<String, List<Animal>> createAnimals(AnimalType animalType) {
        int i = 0;
        Random rand = new Random();
        ConcurrentMap<String, List<Animal>> res = new ConcurrentHashMap<>();
        while (i++ < 50) {
            AbstractAnimal animal;
            switch (animalType) {
                case DOG -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Dog("Дружок " + i, "Немецкая овчарка", BigDecimal.valueOf(30000), "Верный", birthDate);
                    res.getOrDefault("DOG", new ArrayList<>()).add(animal);
                }
                case CAT -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Cat("Барсик " + i, "Британская", BigDecimal.valueOf(10000), "Ласковый", birthDate);
                    res.getOrDefault("CAT", new ArrayList<>()).add(animal);
                }
                case WOLF -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Wolf("Клык " + i, "Обыкновенный", BigDecimal.valueOf(50000), "Агрессивный", birthDate);
                    res.getOrDefault("WOLF", new ArrayList<>()).add(animal);
                }
                case SHARK -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Shark("Челюсть " + i, "Белая акула", BigDecimal.valueOf(100000), "Злая", birthDate);
                    res.getOrDefault("SHARK", new ArrayList<>()).add(animal);
                }
            }
        }
        return res;
    }
}
