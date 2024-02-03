package services;

import animals.AnimalType;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import java.util.Random;

public class CreateAnimalServiceBeanPostProcessor implements BeanPostProcessor {

    /**
     * Функция, задающая бину {@link CreateAnimalService} случайный тип генерируемеого животного
     *
     * @return бин с установленным {@link AnimalType}
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass() == CreateAnimalServiceImpl.class) {
            Random rand = new Random();
            int index = rand.nextInt(AnimalType.values().length);
            AnimalType animalType = AnimalType.values()[index];
            ((CreateAnimalServiceImpl) bean).setAnimalType(animalType);
        }
        return bean;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
