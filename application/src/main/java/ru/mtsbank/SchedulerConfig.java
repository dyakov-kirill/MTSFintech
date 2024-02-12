package ru.mtsbank;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.repositories.AnimalRepository;

import java.util.ArrayList;

@Component
public class SchedulerConfig {

    private final AnimalRepository repository;

    public SchedulerConfig(AnimalRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 60000)
    public void printResults() {
        ArrayList<Animal> olderAnimal = repository.findOlderAnimal(10);
        System.out.println("Older than 10:");
        for (Animal animal : olderAnimal) {
            System.out.println(animal.getName() + ", year of birth - " + animal.getBirthDate().getYear());
        }
        ArrayList<String> leapYearNames = repository.findLeapYearNames();
        System.out.println("Animals, which was born in leap year:");
        for (String animalName : leapYearNames) {
            System.out.println(animalName);
        }
        repository.printDuplicate();
    }
}
