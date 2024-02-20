package ru.mtsbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.repositories.AnimalRepository;
import ru.mtsbank.repositories.AnimalRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;

@SpringBootTest(classes = ApplicationConfiguration.class)
public class ApplicationTest {

    @Autowired
    private AnimalRepositoryImpl animalRepository;
    @Test
    @DisplayName("Поднятие контекста")
    public void contextLoads(ApplicationContext context) {
        Assertions.assertNotNull(context);
    }

    @Test
    @DisplayName("Создание бина")
    public void serviceBeanCreated(ApplicationContext context) {
        Assertions.assertNotNull(context.getBean("animalRepositoryImpl"));
    }

    @Test
    @DisplayName("Создание планировщика")
    public void schedulerConfigCreated(ApplicationContext context) {
        Assertions.assertNotNull(context.getBean("schedulerConfig"));
    }

    @Nested
    public class positiveScenarios {
        @Test
        @DisplayName("Поиск дубликатов")
        public void findDuplicate() {
            ArrayList<Animal> animals = new ArrayList<>();
            animals.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            animals.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(animalRepository.findDuplicate().size(), 1);
        }

        @Test
        @DisplayName("Поиск високосных лет")
        public void findLeapYears() {
            ArrayList<Animal> animals = new ArrayList<>();
            animals.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            animals.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(animalRepository.findLeapYearNames().size(), 1);
        }

        @Test
        @DisplayName("Поиск животных страше 10")
        public void findOlderThan10() {
            ArrayList<Animal> animals = new ArrayList<>();
            animals.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1999, 1, 1)));
            animals.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(animalRepository.findOlderAnimal(10).size(), 1);
        }
    }

    @Nested
    public class negativeScenarios {
        @Test
        @DisplayName("Поиск дубликатов при отсутствии массива животных")
        public void nullDuplicate() {
            animalRepository.setAnimals(null);
            Assertions.assertEquals(animalRepository.findDuplicate().size(), 0);
        }

        @Test
        @DisplayName("Поиск високосных лет при отсутствии массива животных")
        public void nullLeapYears() {
            animalRepository.setAnimals(null);
            Assertions.assertEquals(animalRepository.findLeapYearNames().size(), 0);
        }

        @Test
        @DisplayName("Поиск животных страше 10 при отсутствии массива животных")
        public void nullOlderThan10() {
            animalRepository.setAnimals(null);
            Assertions.assertEquals(animalRepository.findOlderAnimal(10).size(), 0);
        }
    }
}
