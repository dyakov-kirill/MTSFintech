import animals.Animal;
import animals.Cat;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import services.SearchService;
import services.SearchServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


class FindAnimalTest {
    public static SearchService searchService;
    public static ArrayList<Animal> animalList;
    public static ArrayList<String> leapYearNameList;
    public static ArrayList<Animal> older3Animal;
    public static ArrayList<Animal> older14Animal;
    @BeforeAll
    public static void beforeAll() {
        searchService = new SearchServiceImpl();
        animalList = new ArrayList<>();
        leapYearNameList = new ArrayList<>();
        older3Animal = new ArrayList<>();
        older14Animal = new ArrayList<>();
        for (int i = 1; i <= 20; i++) {
            Animal animal = new Cat("" + i, "1", BigDecimal.valueOf(1), "1", LocalDate.of(2000 + i, 1, 1));
            animalList.add(animal);
            if (i % 4 == 0) {
                leapYearNameList.add(animal.getName());
            }
            older3Animal.add(animal);
            if (i < 10) {
                older14Animal.add(animal);
            }
        }
    }

    @Nested
    class FindLeapYearNamesTests {
        @Test
        @DisplayName("Передача null в параметр")
        void nullTest() {
            Assertions.assertEquals(searchService.findLeapYearNames(null).size(), 0);
        }

        @Test
        @DisplayName("Передача пустого списка")
        void emptyTest() {
            Assertions.assertEquals(searchService.findLeapYearNames(new ArrayList<>()).size(), 0);
        }

        @Test
        @DisplayName("Передача списка с данными")
        void normalTest() {
            Assertions.assertIterableEquals(searchService.findLeapYearNames(animalList), leapYearNameList);
        }
    }

    @Nested
    class FindOlderAnimalTests {
        @Test
        @DisplayName("Передача null в параметр")
        void nullTest() {
            Assertions.assertEquals(searchService.findOlderAnimal(null, 5).size(), 0);
        }

        @Test
        @DisplayName("Передача пустого списка")
        void emptyTest() {
            Assertions.assertEquals(searchService.findOlderAnimal(new ArrayList<>(), 5).size(), 0);
        }

        @ParameterizedTest
        @ValueSource(ints = {0, 1, 2, 3})
        @DisplayName("Передача списка со значениями #1")
        void normalTest1(int input) {
            Assertions.assertIterableEquals(searchService.findOlderAnimal(animalList, input), older3Animal);
        }

        @Test
        @DisplayName("Передача списка со значениями #2")
        void normalTest2() {
            Assertions.assertIterableEquals(searchService.findOlderAnimal(animalList, 14), older14Animal);
        }
    }

    @Nested
    class FindDuplicateTests {
        @Test
        @DisplayName("Передача null в параметр")
        void nullTest() {
            Assertions.assertEquals(searchService.findDuplicate(null).size(), 0);
        }

        @Test
        @DisplayName("Передача пустого списка")
        void emptyTest() {
            Assertions.assertEquals(searchService.findDuplicate(new ArrayList<>()).size(), 0);
        }

        @Test
        @DisplayName("Передача списка без дубликатов")
        void noDuplicateTest() {
            Assertions.assertEquals(searchService.findDuplicate(animalList).size(), 0);
        }

        @Test
        @DisplayName("Передача списка с дубликатами")
        void withDuplicateTest() {
            animalList.add(animalList.get(1));
            animalList.add(animalList.get(5));
            ArrayList<Animal> res = new ArrayList<>(List.of(new Animal[]{animalList.get(1), animalList.get(5)}));
            Assertions.assertIterableEquals(searchService.findDuplicate(animalList), res);
            animalList.remove(animalList.size() - 1);
            animalList.remove(animalList.size() - 1);
        }
    }
}
