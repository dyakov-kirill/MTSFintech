import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import services.CreateAnimalService;
import services.CreateAnimalServiceBeanPostProcessor;
import services.CreateAnimalServiceImpl;

@Configuration
@ComponentScan
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
}
