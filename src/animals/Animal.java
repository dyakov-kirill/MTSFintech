package animals;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface Animal {
    /**
     * Функция получения породы животного
     *
     * @return возвращает название породы
     */
    String getBreed();

    /**
     * Функция получения имени животного
     *
     * @return возвращает имя животного
     */
    String getName();

    /**
     * Функция получения стоимости животного
     *
     * @return возвращает стоимость животного
     */
    BigDecimal getCost();

    /**
     * Функция получения характера животного
     *
     * @return возвращает характер животного
     */
    String getChar();

    /**
     * Функция получения характера животного
     *
     * @return возвращает характер животного
     */
    LocalDate getBirthDate();
}
