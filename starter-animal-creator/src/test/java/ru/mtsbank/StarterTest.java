package ru.mtsbank;

import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.mtsbank.services.CreateAnimalService;
import ru.mtsbank.services.CreateAnimalServiceImpl;

@SpringBootTest(classes = StarterConfiguration.class)
public class Test {
    @Autowired
    private CreateAnimalServiceImpl createAnimalService;

    @org.junit.jupiter.api.Test
    public void create10Animals() {
        Assertions.assertEquals(createAnimalService.createAnimals(10).size(), 10);
    }

}
