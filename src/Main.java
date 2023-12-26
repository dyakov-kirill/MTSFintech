import animals.Animal;
import animals.AnimalType;
import animals.Cat;
import services.CreateAnimalService;
import services.CreateAnimalServiceImpl;
import services.SearchService;
import services.SearchServiceImpl;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        CreateAnimalServiceImpl createService = new CreateAnimalServiceImpl();
        SearchService searchService = new SearchServiceImpl();
        ArrayList<Animal> animals = new ArrayList<>();
        animals.addAll(createService.createAnimals(AnimalType.CAT));
        animals.addAll(createService.createAnimals(AnimalType.DOG));
        ArrayList<String> leapYearName = searchService.findLeapYearNames(animals);
        ArrayList<Animal> olderThan10 = searchService.findOlderAnimal(animals, 10);
        Animal tmp = animals.get(5);
        Cat duplicate = new Cat(tmp.getName(), tmp.getBreed(), tmp.getCost(), tmp.getChar(), tmp.getBirthDate());
        animals.add(duplicate);
        searchService.findDuplicate(animals);
        System.out.println("Leap year names:");
        for (String name : leapYearName) {
            System.out.printf("%s\n", name);
        }
        System.out.println("Older than 10 years animals:");
        for (Animal animal : olderThan10) {
            System.out.printf("%s was born in %d\n", animal.getName(), animal.getBirthDate().getYear());
        }
    }
}
