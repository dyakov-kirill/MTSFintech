package ru.mtsbank;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.repositories.AnimalRepository;

import java.util.ArrayList;

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}
