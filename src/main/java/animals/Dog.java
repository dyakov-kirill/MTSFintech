package animals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Random;

public class Dog extends Pet{
    public Dog(String name, String breed, BigDecimal cost, String character, LocalDate birthDate) {
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
    }
    public Dog() {
        name = "Дружок";
        breed = "Немецкая овчарка";
        cost = new BigDecimal(30000);
        character = "Верный";
        birthDate = LocalDate.now();
    }
    @Override
    public String getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public String getChar() {
        return character;
    }

    @Override
    public LocalDate getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj.getClass() != Dog.class) {
            return false;
        }
        Dog otherDog = (Dog) obj;
        return Objects.equals(breed, otherDog.getBreed())
                && Objects.equals(name, otherDog.getName())
                && Objects.equals(cost, otherDog.getCost())
                && Objects.equals(character, otherDog.getChar())
                && Objects.equals(birthDate, otherDog.getBirthDate());
    }
}
