package ru.mtsbank;

import org.springframework.boot.SpringApplication;
import ru.mtsbank.spring.ApplicationConfiguration;

public class Application {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationConfiguration.class, args);
    }
}
