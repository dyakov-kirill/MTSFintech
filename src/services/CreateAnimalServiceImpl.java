package services;

import animals.AbstractAnimal;
import animals.Cat;
import animals.Dog;
import animals.Wolf;

public class CreateAnimalServiceImpl implements CreateAnimalService{
    @Override
    public void createAnimals() {
        System.out.print("Overrided method\n");
        int i = 0;
        do {
            AbstractAnimal animal = new Dog();
            System.out.printf("%d, New unique animal created! Hash: %d\n", i, animal.hashCode());
        } while(i++ < 9);
    }

    public void createAnimals(int N) {
        System.out.print("Overloaded method\n");
        for (int i = 0; i < N; i++) {
            AbstractAnimal animal = new Wolf();
            System.out.printf("%d, New unique animal created! Hash: %d\n", i, animal.hashCode());
        }
    }

}
