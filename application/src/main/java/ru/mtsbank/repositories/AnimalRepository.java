package ru.mtsbank.repositories;

import org.springframework.stereotype.Repository;
import ru.mtsbank.entity.Animal;
import ru.mtsbank.repositories.exceptions.WrongListArgumentSize;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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
    Map<String, List<Animal>> findDuplicate();

    /**
     * Функция, выводящая имена повторяющихся в массиве животных
     */
    void printDuplicate();

    /**
     * Печатает средний возраст животных в списке
     *
     * @param animals список животных
     */
    void findAverageAge(List<Animal> animals);

    /**
     * Ищет животных старше 5 лет и стоимостью, выше средней по списку
     *
     * @param animals список животных
     * @return список животных, отсортированный по дате рождения
     */
    List<Animal> findOldAndExpensive(List<Animal> animals);


    /**
     * Ищет животных с минимальной ценой
     *
     * @param animals список животных
     * @return список имен, отсортированный в обратном алфавитном порядке
     */
    List<String> findMinCostAnimals(List<Animal> animals) throws WrongListArgumentSize;

    /**
     * Возвращает коллекцию с животными
     *
     * @return мапа с животными
     */
    Map<String, List<Animal>> getAnimals();

    /**
     * Добавляет N дубликатов животных
     *
     * @param n количество дубликатов
     */
    void addDuplicates(int n);
}
