package ru.mtsbank.repositories;

import org.springframework.stereotype.Repository;
import ru.mtsbank.animals.Animal;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

@Repository
public interface AnimalRepository {
    /**
     * Функция, возвращающая массив имен животных, родившихся в виоскосный год
     *
     * @return массив животных, родившихся в високосный год
     */
    Map<String, LocalDate> findLeapYearNames();

    /**
     * Функция, возвращающая массив животных, чей возраст больше N
     *
     * @param N возраст
     * @return массив животных, возраст которых больше N
     */
    Map<Animal, Integer> findOlderAnimal(int N);

    /**
     * Функция, возвращающая массив повторяющихся в массиве животных
     *
     * @return список дубликатов
     */
    Map<String, Integer> findDuplicate();

    /**
     * Функция, выводящая имена повторяющихся в массиве животных
     */
    void printDuplicate();
}
