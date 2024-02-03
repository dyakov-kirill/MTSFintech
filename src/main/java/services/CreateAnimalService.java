package services;

import animals.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public interface CreateAnimalService {

    default ArrayList<Animal> createAnimals(AnimalType animalType) {
        int i = 0;
        Random rand = new Random();
        ArrayList<Animal> res = new ArrayList<>();
        while (i++ < 10) {
            AbstractAnimal animal;
            switch (animalType) {
                case DOG -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Dog("Дружок " + i, "Немецкая овчарка", BigDecimal.valueOf(30000), "Верный", birthDate);
                    res.add(animal);
                }
                case CAT -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Cat("Барсик " + i, "Британская", BigDecimal.valueOf(10000), "Ласковый", birthDate);
                    res.add(animal);
                }
                case WOLF -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Wolf("Клык " + i, "Обыкновенный", BigDecimal.valueOf(50000), "Агрессивный", birthDate);
                    res.add(animal);
                }
                case SHARK -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Shark("Челюсть " + i, "Белая акула", BigDecimal.valueOf(100000), "Злая", birthDate);
                    res.add(animal);
                }
            }
        }
        return res;
    }
}
