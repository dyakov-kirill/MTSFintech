package ru.mtsbank;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import ru.mtsbank.entity.Animal;
import ru.mtsbank.entity.AnimalType;
import ru.mtsbank.entity.Cat;
import ru.mtsbank.entity.Dog;
import ru.mtsbank.repositories.AnimalRepositoryImpl;
import ru.mtsbank.repositories.exceptions.WrongListArgumentSize;
import ru.mtsbank.spring.ApplicationConfiguration;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

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
            List<Animal> list1 = new ArrayList<>();
            List<Animal> list2 = new ArrayList<>();
            ConcurrentMap<String, List<Animal>> animals = new ConcurrentHashMap<>();
            list1.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            list1.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            list1.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            list2.add(new Dog("3", "2", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            list2.add(new Dog("3", "2", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            list2.add(new Dog("4", "2", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            list2.add(new Dog("4", "2", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1)));
            animals.put(AnimalType.CAT.toString(), list1);
            animals.put(AnimalType.DOG.toString(), list2);
            animalRepository.setAnimals(animals);
            Assertions.assertIterableEquals(List.of(list1.get(0)), animalRepository.findDuplicate().get(AnimalType.CAT.toString()));
            Assertions.assertIterableEquals(List.of(list2.get(0), list2.get(2)), animalRepository.findDuplicate().get(AnimalType.DOG.toString()));
        }

        @Test
        @DisplayName("Поиск високосных лет")
        public void findLeapYears() {
            List<Animal> list = new ArrayList<>();
            ConcurrentMap<String, List<Animal>> animals = new ConcurrentHashMap<>();
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
            ConcurrentMap<String, List<Animal>> animals = new ConcurrentHashMap<>();
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1999, 1, 1)));
            list.add(new Cat("2", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            animals.put(AnimalType.CAT.toString(), list);
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(25, animalRepository.findOlderAnimal(10).get(list.get(0)));
        }

        @Test
        @DisplayName("Поиск животных старше 5 и дороже средней цены по списку")
        public void oldAndExpensive() {
            List<Animal> list = new ArrayList<>();
            list.add(new Cat("1", "1", BigDecimal.valueOf(10), "1", LocalDate.of(1999, 1, 1)));
            list.add(new Cat("2", "1", BigDecimal.valueOf(7), "1", LocalDate.of(2012, 1, 1)));
            list.add(new Cat("3", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            Assertions.assertIterableEquals(List.of(list.get(0), list.get(1)), animalRepository.findOldAndExpensive(list));
        }

        @Test
        @DisplayName("Поиск животных c минимальной ценой")
        public void minCost() {
            List<Animal> list = new ArrayList<>();
            list.add(new Cat("1", "1", BigDecimal.valueOf(10), "1", LocalDate.of(1999, 1, 1)));
            list.add(new Cat("2", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2012, 1, 1)));
            list.add(new Cat("3", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            try {
                Assertions.assertIterableEquals(List.of(list.get(2).getName(), list.get(1).getName()), animalRepository.findMinCostAnimals(list));
            } catch (WrongListArgumentSize e) {
                throw new RuntimeException(e);
            }
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
            ConcurrentMap<String, List<Animal>> animals = new ConcurrentHashMap<>();
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1999, 1, 1)));
            list.add(new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(2024, 1, 1)));
            animals.put(AnimalType.CAT.toString(), list);
            animalRepository.setAnimals(animals);
            Assertions.assertEquals(animalRepository.findOlderAnimal(50).get(list.get(0)), 25);
        }

    }
}
