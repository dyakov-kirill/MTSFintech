package services;

import animals.*;

import java.util.Random;

public class CreateAnimalServiceImpl implements CreateAnimalService {
    @Override
    public void createAnimals() {
        Random rand = new Random();
        System.out.print("Overrided method\n");
        int i = 0;
        do {
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
        } while (i++ < 9);
    }

    public void createAnimals(int N) {
        Random rand = new Random();
        System.out.print("Overloaded method\n");
        for (int i = 0; i < N; i++) {
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
