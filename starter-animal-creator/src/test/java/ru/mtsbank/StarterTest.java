package ru.mtsbank;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import ru.mtsbank.services.CreateAnimalServiceImpl;

@SpringBootTest(classes = StarterTestConfiguration.class)
@ActiveProfiles("test")
public class StarterTest {
    @Autowired
    private CreateAnimalServiceImpl createAnimalService;

    @Test
    @DisplayName("Поднятие контекста")
    public void contextLoads(ApplicationContext context) {
        Assertions.assertNotNull(context);
    }

    @Test
    @DisplayName("Создание бина")
    public void serviceBeanCreated(ApplicationContext context) {
        Assertions.assertNotNull(context.getBean("animalServiceBean"));
    }

    @Test
    @DisplayName("Создание постпроцессора")
    public void postProcessorBeanCreated(ApplicationContext context) {
        Assertions.assertNotNull(context.getBean("beanPostProcessor"));
    }

    @Nested
    public class positiveScenarios {
        @Test
        @DisplayName("Создание 10 животных")
        public void create10Animals() {
            Assertions.assertEquals(10, createAnimalService.createAnimals(10).size());
        }

        @Test
        @DisplayName("Создание 100 животных")
        public void create1000Animals() {
            Assertions.assertEquals(100, createAnimalService.createAnimals(100).size());
        }
    }

    @Nested
    public class negativeScenarios {
        @Test
        @DisplayName("Передача отрицательного параметра")
        public void negativeParameter() {
            Assertions.assertEquals(0, createAnimalService.createAnimals(-1).size());
        }

        @Test
        @DisplayName("Передача null в качестве параметра")
        public void nullParameter() {
            Assertions.assertEquals(0, createAnimalService.createAnimals((Integer)null).size());
        }
    }
}
