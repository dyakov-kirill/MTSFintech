package ru.mtsbank.spring;

import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.mtsbank.entity.Animal;
import ru.mtsbank.entity.Cat;
import ru.mtsbank.repositories.AnimalRepository;
import ru.mtsbank.repositories.exceptions.NegativeArgumentException;
import ru.mtsbank.repositories.exceptions.NullPointerArgumentException;
import ru.mtsbank.repositories.exceptions.WrongListArgumentSize;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Component
public class SchedulerConfig {

    private final AnimalRepository repository;

    public SchedulerConfig(AnimalRepository repository) {
        this.repository = repository;
        //repository.addDuplicates(4);
    }

    @PostConstruct
    private void postConstruct() {
        repository.addDuplicates(4);
        Thread printThread = new Thread("PrintDuplicateThread") {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.print(this.getName() + ": \n");
                        repository.printDuplicate();
                        Thread.sleep(10000);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        Thread findAverageThread = new Thread("FindAverageAgeThread") {
            @Override
            public void run() {
                try {
                    while (true) {
                        System.out.print(this.getName() + ": \n");
                        List<Animal> animalsList = repository.getAnimals().values().stream().flatMap(List::stream).toList();
                        repository.findAverageAge(animalsList);
                        Thread.sleep(20500);
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };

        printThread.start();
        findAverageThread.start();
    }

    @Scheduled(fixedDelay = 60000)
    public void printResults() {
        try {
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
            repository.findMinCostAnimals(List.of(new Cat("1", "1", BigDecimal.ONE, "1", LocalDate.of(1, 1, 1))));
        } catch (NegativeArgumentException e) {
            System.out.println("Negative argument exception was thrown. Message: " + e.getMessage());
        } catch (NullPointerArgumentException e) {
            System.out.println("Null pointer argument exception was thrown. Message: " + e.getMessage());
        } catch (WrongListArgumentSize e) {
            System.out.println("Wrong list size exception was thrown. Message: " + e.getMessage());
        }
    }
}
