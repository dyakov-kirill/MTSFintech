package ru.mtsbank;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
@EnableScheduling
public class ApplicationConfiguration {

}
