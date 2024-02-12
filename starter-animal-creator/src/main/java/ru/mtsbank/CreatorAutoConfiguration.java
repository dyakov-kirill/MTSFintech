package ru.mtsbank;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.mtsbank.services.CreateAnimalService;
import ru.mtsbank.services.CreateAnimalServiceBeanPostProcessor;
import ru.mtsbank.services.CreateAnimalServiceImpl;

@Configuration
@ConditionalOnClass(CreateAnimalServiceImpl.class)
public class CreatorAutoConfiguration {

    @Bean
    @Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public CreateAnimalService animalServiceBean() {
        return new CreateAnimalServiceImpl();
    }

    @Bean
    static public CreateAnimalServiceBeanPostProcessor beanPostProcessor() {
        return new CreateAnimalServiceBeanPostProcessor();
    }
}