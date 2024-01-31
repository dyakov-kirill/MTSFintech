package services;

import animals.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    /**
     * Функция, возвращающая 10 животных типа animalType
     *
     * @param animalType тип возвращаемых животных
     * @return массив из 10 животных типа animalType
     */
    @Override
    public ArrayList<Animal> createAnimals(AnimalType animalType) {
        Random rand = new Random();
        ArrayList<Animal> res = new ArrayList<>();
        int i = 0;
        do {
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
        } while (i++ < 9);
        return res;
    }

    /**
     * Функция, возвращающая N животных типа animalType
     *
     * @param animalType тип возвращаемых животных
     * @param N          количество возвращаемых животных
     * @return массив из N животных типа animalType
     */
    public ArrayList<Animal> createAnimals(AnimalType animalType, int N) {
        Random rand = new Random();
        ArrayList<Animal> res = new ArrayList<>();
        for (int i = 0; i < N; i++) {
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
