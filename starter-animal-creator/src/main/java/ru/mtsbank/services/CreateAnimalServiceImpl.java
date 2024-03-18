package ru.mtsbank.services;

import org.springframework.beans.factory.annotation.Value;
import ru.mtsbank.entity.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

public class CreateAnimalServiceImpl implements CreateAnimalService {

    @Value("#{'${animal.cat.names}'.split(',')}")
    private List<String> catNames;

    @Value("#{'${animal.dog.names}'.split(',')}")
    private List<String> dogNames;

    @Value("#{'${animal.wolf.names}'.split(',')}")
    private List<String> wolfNames;

    @Value("#{'${animal.shark.names}'.split(',')}")
    private List<String> sharkNames;


    private AnimalType animalType;

    /**
     * Функция, возвращающая 10 животных типа animalType
     *
     * @param animalType тип возвращаемых животных
     * @return массив из 10 животных типа animalType
     */
    @Override
    public Map<String, List<Animal>> createAnimals(AnimalType animalType) {
        Random rand = new Random();
        Map<String, List<Animal>> res = new HashMap<>();
        AnimalType actualType = animalType;
        if (this.animalType != null) {
            actualType = this.animalType;
        }
        int i = 0;
        do {
            AbstractAnimal animal;
            switch (actualType) {
                case DOG -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Dog(dogNames.get(rand.nextInt(dogNames.size())), "Shepherd", BigDecimal.valueOf(30000), "Faithful", birthDate);
                    if (res.containsKey("DOG")) {
                        res.getOrDefault("DOG", new ArrayList<>()).add(animal);
                    } else {
                        List<Animal> list = new ArrayList<>();
                        list.add(animal);
                        res.put("DOG", list);
                    }
                }
                case CAT -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Cat(catNames.get(rand.nextInt(catNames.size())), "British", BigDecimal.valueOf(10000), "Gentle", birthDate);
                    if (res.containsKey("CAT")) {
                        res.getOrDefault("CAT", new ArrayList<>()).add(animal);
                    } else {
                        List<Animal> list = new ArrayList<>();
                        list.add(animal);
                        res.put("CAT", list);
                    }
                }
                case WOLF -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Wolf(wolfNames.get(rand.nextInt(wolfNames.size())), "Default", BigDecimal.valueOf(50000), "Aggressive", birthDate);
                    if (res.containsKey("WOLF")) {
                        res.getOrDefault("WOLF", new ArrayList<>()).add(animal);
                    } else {
                        List<Animal> list = new ArrayList<>();
                        list.add(animal);
                        res.put("WOLF", list);
                    }
                }
                case SHARK -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Shark(sharkNames.get(rand.nextInt(sharkNames.size())), "White shark", BigDecimal.valueOf(100000), "Angry", birthDate);
                    if (res.containsKey("SHARK")) {
                        res.getOrDefault("SHARK", new ArrayList<>()).add(animal);
                    } else {
                        List<Animal> list = new ArrayList<>();
                        list.add(animal);
                        res.put("SHARK", list);
                    }
                }
            }
        } while (i++ < 9);
        return res;
    }

    /**
     * Функция, возвращающая N животных типа animalType
     *
     * @param N          количество возвращаемых животных
     * @return массив из N животных типа animalType
     */
    public Map<String, List<Animal>> createAnimals(Integer N) {
        if (N == null || N <= 0) {
            return new HashMap<String, List<Animal>>();
        }
        Random rand = new Random();
        Map<String, List<Animal>> res = new HashMap<>();
        System.out.println(catNames);
        for (int i = 0; i < N; i++) {
            AbstractAnimal animal;
            switch (animalType) {
                case DOG -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Dog(dogNames.get(rand.nextInt(dogNames.size())), "Shepherd", BigDecimal.valueOf(30000), "Faithful", birthDate);
                    if (res.containsKey("DOG")) {
                        res.getOrDefault("DOG", new ArrayList<>()).add(animal);
                    } else {
                        List<Animal> list = new ArrayList<>();
                        list.add(animal);
                        res.put("DOG", list);
                    }
                }
                case CAT -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Cat(catNames.get(rand.nextInt(catNames.size())), "British", BigDecimal.valueOf(10000), "Gentle", birthDate);
                    if (res.containsKey("CAT")) {
                        res.getOrDefault("CAT", new ArrayList<>()).add(animal);
                    } else {
                        List<Animal> list = new ArrayList<>();
                        list.add(animal);
                        res.put("CAT", list);
                    }
                }
                case WOLF -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Wolf(wolfNames.get(rand.nextInt(wolfNames.size())), "Default", BigDecimal.valueOf(50000), "Aggressive", birthDate);
                    if (res.containsKey("WOLF")) {
                        res.getOrDefault("WOLF", new ArrayList<>()).add(animal);
                    } else {
                        List<Animal> list = new ArrayList<>();
                        list.add(animal);
                        res.put("WOLF", list);
                    }
                }
                case SHARK -> {
                    LocalDate birthDate = LocalDate.ofEpochDay(rand.nextLong(0, LocalDate.now().toEpochDay()));
                    animal = new Shark(sharkNames.get(rand.nextInt(sharkNames.size())), "White shark", BigDecimal.valueOf(100000), "Angry", birthDate);
                    if (res.containsKey("SHARK")) {
                        res.getOrDefault("SHARK", new ArrayList<>()).add(animal);
                    } else {
                        List<Animal> list = new ArrayList<>();
                        list.add(animal);
                        res.put("SHARK", list);
                    }
                }
            }
        }
        return res;
    }

    public AnimalType getAnimalType() {
        return animalType;
    }

    public void setAnimalType(AnimalType animalType) {
        this.animalType = animalType;
    }
}
