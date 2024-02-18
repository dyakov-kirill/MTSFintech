import ru.mtsbank.animals.Cat;
import ru.mtsbank.animals.Dog;
import ru.mtsbank.animals.Shark;
import ru.mtsbank.animals.Wolf;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.time.LocalDate;


class EqualsTest {
    @Nested
    class CatTests {
        private static Cat cat1;
        private static Cat cat2;
        private static Cat cat3;

        @BeforeAll
        public static void beforeAll() {
            cat1 = new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
            cat2 = new Cat("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
            cat3 = new Cat("2", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
        }
        @Test
        @DisplayName("Сравнение с самим собой")
        void reflexivityTest() {
            Assertions.assertEquals(cat1, cat1);
        }

        @Test
        @DisplayName("Сравнение с null")
        void nullTest() {
            Assertions.assertNotEquals(null, cat1);
        }

        @Test
        @DisplayName("Сравнение с таким же объектом")
        void symmetryTest() {
            Assertions.assertEquals(cat1, cat2);
        }

        @Test
        @DisplayName("Сравнение с отличающимся объектом")
        void falseTest() {
            Assertions.assertNotEquals(cat1, cat3);
        }

    }

    @Nested
    class DogTests {
        private static Dog dog1;
        private static Dog dog2;
        private static Dog dog3;

        @BeforeAll
        public static void beforeAll() {
            dog1 = new Dog("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
            dog2 = new Dog("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
            dog3 = new Dog("2", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
        }

        @Test
        @DisplayName("Сравнение с самим собой")
        void reflexivityTest() {
            Assertions.assertEquals(dog1, dog1);
        }

        @Test
        @DisplayName("Сравнение с null")
        void nullTest() {
            Assertions.assertNotEquals(null, dog1);
        }

        @Test
        @DisplayName("Сравнение с таким же объектом")
        void symmetryTest() {
            Assertions.assertEquals(dog1, dog2);
        }

        @Test
        @DisplayName("Сравнение с отличающимся объектом")
        void falseTest() {
            Assertions.assertNotEquals(dog1, dog3);
        }
    }

    @Nested
    class WolfTests {
        private static Wolf wolf1;
        private static Wolf wolf2;
        private static Wolf wolf3;

        @BeforeAll
        public static void beforeAll() {
            wolf1 = new Wolf("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
            wolf2 = new Wolf("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
            wolf3 = new Wolf("2", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
        }

        @Test
        @DisplayName("Сравнение с самим собой")
        void reflexivityTest() {
            Assertions.assertEquals(wolf1, wolf1);
        }

        @Test
        @DisplayName("Сравнение с null")
        void nullTest() {
            Assertions.assertNotEquals(null, wolf1);
        }

        @Test
        @DisplayName("Сравнение с таким же объектом")
        void symmetryTest() {
            Assertions.assertEquals(wolf1, wolf2);
        }

        @Test
        @DisplayName("Сравнение с отличающимся объектом")
        void falseTest() {
            Assertions.assertNotEquals(wolf1, wolf3);
        }
    }

    @Nested
    class SharkTests {
        private static Shark shark1;
        private static Shark shark2;
        private static Shark shark3;

        @BeforeAll
        public static void beforeAll() {
            shark1 = new Shark("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
            shark2 = new Shark("1", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
            shark3 = new Shark("2", "1", BigDecimal.valueOf(1), "1", LocalDate.of(1, 1, 1));
        }

        @Test
        @DisplayName("Сравнение с самим собой")
        void reflexivityTest() {
            Assertions.assertEquals(shark1, shark1);
        }

        @Test
        @DisplayName("Сравнение с null")
        void nullTest() {
            Assertions.assertNotEquals(null, shark1);
        }

        @Test
        @DisplayName("Сравнение с таким же объектом")
        void symmetryTest() {
            Assertions.assertEquals(shark1, shark2);
        }

        @Test
        @DisplayName("Сравнение с отличающимся объектом")
        void falseTest() {
            Assertions.assertNotEquals(shark1, shark3);
        }
    }
}

