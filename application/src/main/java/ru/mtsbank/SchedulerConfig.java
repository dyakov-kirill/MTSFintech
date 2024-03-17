package ru.mtsbank;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.entity.Animal;
import ru.mtsbank.repositories.AnimalRepository;

import java.time.LocalDate;
import java.util.Map;

@Component
public class SchedulerConfig {

    private final AnimalRepository repository;

    public SchedulerConfig(AnimalRepository repository) {
        this.repository = repository;
    }

    @Scheduled(fixedDelay = 60000)
    public void printResults() {
        Map<Animal, Integer> olderAnimal = repository.findOlderAnimal(10);
        System.out.println("Older than 10:");
        for (Animal animal : olderAnimal.keySet()) {
            System.out.println(animal.getName() + ", year of birth - " + animal.getBirthDate().getYear());
        }
        Map<String, LocalDate> leapYearNames = repository.findLeapYearNames();
        System.out.println("Animals, which was born in leap year:");
        for (String key : leapYearNames.keySet()) {
            System.out.println(key + " = " + leapYearNames.get(key));
        }
        repository.printDuplicate();
    }
}
