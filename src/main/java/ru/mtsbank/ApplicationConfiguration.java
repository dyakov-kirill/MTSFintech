package ru.mtsbank;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import ru.mtsbank.animals.Animal;
import ru.mtsbank.repositories.AnimalRepository;
import ru.mtsbank.services.CreateAnimalService;
import ru.mtsbank.services.CreateAnimalServiceBeanPostProcessor;
import ru.mtsbank.services.CreateAnimalServiceImpl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@SpringBootApplication
@PropertySource(value = "classpath:application.properties")
@EnableScheduling
public class ApplicationConfiguration {
    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CreateAnimalService animalServiceBean() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    static public CreateAnimalServiceBeanPostProcessor beanPostProcessor() {
        return new CreateAnimalServiceBeanPostProcessor();
    }

//    @Scheduled(fixedDelay = 10000)
//    public void logger() {
//        System.out.println((new SimpleDateFormat("HH:mm:ss")).format(new Date()));
//    }


}
