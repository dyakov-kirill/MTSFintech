package ru.mtsbank;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mtsbank.services.CreateAnimalServiceBeanPostProcessor;
import ru.mtsbank.services.CreateAnimalServiceImpl;

@TestConfiguration
public class StarterTestConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CreateAnimalServiceImpl animalServiceImplBean() {
        return new CreateAnimalServiceImpl();
    }

}
