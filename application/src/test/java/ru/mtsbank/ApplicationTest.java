package ru.mtsbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.animals.AnimalType;
import ru.mtsbank.animals.Cat;
import ru.mtsbank.repositories.AnimalRepository;
import ru.mtsbank.repositories.AnimalRepositoryImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
            List<Animal> list = new ArrayList<>();
            Map<String, List<Animal>> animals = new HashMap<>();
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            animals.put(AnimalType.CAT.toString(), list);
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(1, animalRepository.findDuplicate().get(AnimalType.CAT.toString()));
        }

        @Test
        @DisplayName("Поиск високосных лет")
        public void findLeapYears() {
            List<Animal> list = new ArrayList<>();
            Map<String, List<Animal>> animals = new HashMap<>();
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            list.add(new Cat("2", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            animals.put(AnimalType.CAT.toString(), list);
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(list.get(0).getBirthDate(), animalRepository.findLeapYearNames().get("Cat 1"));
        }

        @Test
        @DisplayName("Поиск животных страше 10")
        public void findOlderThan10() {
            List<Animal> list = new ArrayList<>();
            Map<String, List<Animal>> animals = new HashMap<>();
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1999, 1, 1)));
            list.add(new Cat("2", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            animals.put(AnimalType.CAT.toString(), list);
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(25, animalRepository.findOlderAnimal(10).get(list.get(0)));
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
        @Test
        @DisplayName("Отсутствие животных старше 10")
        public void noOlderThan10() {
            List<Animal> list = new ArrayList<>();
            Map<String, List<Animal>> animals = new HashMap<>();
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1999, 1, 1)));
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            animals.put(AnimalType.CAT.toString(), list);
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(animalRepository.findOlderAnimal(50).get(list.get(0)), 25);
        }

    }
}
