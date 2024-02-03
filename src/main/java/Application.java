import animals.Animal;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import repositories.AnimalRepository;

import java.util.ArrayList;


public class Application {

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
        AnimalRepository repositoryBean = context.getBean(AnimalRepository.class);
        ArrayList<Animal> olderAnimal = repositoryBean.findOlderAnimal(10);
        System.out.println("Животные старше 10 лет:");
        for (Animal animal : olderAnimal) {
            System.out.println(animal.getName() + ", год рождения - " + animal.getBirthDate().getYear());
        }
        ArrayList<String> leapYearNames = repositoryBean.findLeapYearNames();
        System.out.println("Животные, рожденные в високосный год:");
        for (String animalName : leapYearNames) {
            System.out.println(animalName);
        }
        repositoryBean.printDuplicate();
    }
}
