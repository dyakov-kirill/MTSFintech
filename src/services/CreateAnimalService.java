package services;

import animals.*;

import java.util.Random;

public interface CreateAnimalService {
    default void createAnimals() {
        int i = 0;
        Random rand = new Random();
        System.out.print("Default method\n");
        while (i++ < 10) {
            AbstractAnimal animal;
            switch (rand.nextInt(0, 4)) {
                case 0 -> {
                    animal = new Dog();
                    System.out.printf("%d, New dog created! Hash: %d\n", i, animal.hashCode());
                }
                case 1 -> {
                    animal = new Cat();
                    System.out.printf("%d, New cat created! Hash: %d\n", i, animal.hashCode());
                }
                case 2 -> {
                    animal = new Wolf();
                    System.out.printf("%d, New wolf created! Hash: %d\n", i, animal.hashCode());
                }
                case 3 -> {
                    animal = new Shark();
                    System.out.printf("%d, New shark created! Hash: %d\n", i, animal.hashCode());
                }
            }
        }
    }
}
