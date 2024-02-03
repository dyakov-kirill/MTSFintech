package repositories;

import animals.Animal;
import animals.AnimalType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import services.CreateAnimalService;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@Repository
public class AnimalRepositoryImpl implements AnimalRepository {

    private ArrayList<Animal> animals;

    @Autowired
    private CreateAnimalService animalService;

    @PostConstruct
    private void postConstruct() {
        animals = animalService.createAnimals(AnimalType.CAT);
    }

    @Override
    public ArrayList<String> findLeapYearNames() {
        ArrayList<String> res = new ArrayList<>();
        if (animals == null) {
            return res;
        }
        for (Animal animal: animals) {
            if (animal != null && animal.getBirthDate().isLeapYear()) {
                res.add(animal.getName());
            }
        }
        return res;
    }

    @Override
    public ArrayList<Animal> findOlderAnimal(int N) {
        ArrayList<Animal> res = new ArrayList<>();
        if (animals == null) {
            return res;
        }
        for (Animal animal: animals) {
            if (animal != null && LocalDate.now().getYear() - animal.getBirthDate().getYear() > N) {
                res.add(animal);
            }
        }
        return res;
    }

    @Override
    public Set<Animal> findDuplicate() {
        ArrayList<Animal> appearedAnimals = new ArrayList<>();
        HashSet<Animal> duplicate = new HashSet<>();
        if (animals == null) {
            return duplicate;
        }
        for (Animal animal: animals) {
            if (!appearedAnimals.contains(animal)) {
                appearedAnimals.add(animal);
            } else {
                duplicate.add(animal);
            }
        }
        return duplicate;
    }

    @Override
    public void printDuplicate() {
        Set<Animal> duplicates = findDuplicate();
        for (Animal animal: duplicates) {
            System.out.printf("Duplicate founded: %s\n", animal.getName());
        }
    }

    public void setAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }
}
