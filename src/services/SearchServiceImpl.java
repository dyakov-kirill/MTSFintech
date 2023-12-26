package services;

import animals.Animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;

public class SearchServiceImpl implements SearchService {
    @Override
    public ArrayList<String> findLeapYearNames(ArrayList<Animal> animals) {
        ArrayList<String> res = new ArrayList<>();
        for (Animal animal: animals) {
            if (animal != null && animal.getBirthDate().isLeapYear()) {
                res.add(animal.getName());
            }
        }
        return res;
    }

    @Override
    public ArrayList<Animal> findOlderAnimal(ArrayList<Animal> animals, int N) {
        ArrayList<Animal> res = new ArrayList<>();
        for (Animal animal: animals) {
            if (animal != null && LocalDate.now().getYear() - animal.getBirthDate().getYear() > N) {
                res.add(animal);
            }
        }
        return res;
    }

    @Override
    public void findDuplicate(ArrayList<Animal> animals) {
        ArrayList<Animal> appearedAnimals = new ArrayList<>();
        for (Animal animal: animals) {
            if (appearedAnimals.contains(animal)) {
                System.out.printf("Duplicate founded: %s\n", animal.getName());
            } else {
                appearedAnimals.add(animal);
            }
        }
    }
}
