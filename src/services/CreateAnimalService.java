package services;

import animals.AbstractAnimal;
import animals.Cat;

public interface CreateAnimalService {
    default void createAnimals() {
        int i = 0;
        System.out.print("Default method\n");
        while (i++ < 10) {
            AbstractAnimal animal = new Cat();
            System.out.printf("%d, New unique animal created! Hash: %d\n", i, animal.hashCode());
        }
    }
}
