package ru.mtsbank.spring;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
@ComponentScan("ru.mtsbank")
@EnableScheduling
public class ApplicationConfiguration {

}
