package ru.mtsbank.services;


import ru.mtsbank.animals.Animal;

import java.util.ArrayList;

/**
 * Методы перенесены в интерфейс AnimalRepository
 */
@Deprecated
public interface SearchService {
    /**
     * Функция, возвращающая массив имен животных, родившихся в виоскосный год
     *
     * @param animals масив животных
     * @return массив животных, родившихся в високосный год
     */
    ArrayList<String> findLeapYearNames(ArrayList<Animal> animals);

    /**
     * Функция, возвращающая массив животных, чей возраст больше N
     *
     * @param animals масив животных
     * @param N       возраст
     * @return массив животных, возраст которых больше N
     */
    ArrayList<Animal> findOlderAnimal(ArrayList<Animal> animals, int N);

    /**
     * Функция, возвращающая массив повторяющихся в массиве животных
     *
     * @param animals масив животных
     * @return список дубликатов
     */
    ArrayList<Animal> findDuplicate(ArrayList<Animal> animals);

    /**
     * Функция, выводящая имена повторяющихся в массиве животных
     *
     * @param animals масив животных
     */
    void printDuplicate(ArrayList<Animal> animals);
}
