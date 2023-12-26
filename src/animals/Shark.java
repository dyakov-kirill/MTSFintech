package animals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Shark extends Predator {
    public Shark(String name, String breed, BigDecimal cost, String character,  LocalDate birthDate) {
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
    }
    public Shark() {
        name = "Челюсть";
        breed = "Белая акула";
        cost = new BigDecimal(100000);
        character = "Злая";
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
        if (obj == null || obj.getClass() != Shark.class) {
            return false;
        }
        Shark otherShark = (Shark) obj;
        return Objects.equals(breed, otherShark.getBreed())
                && Objects.equals(name, otherShark.getName())
                && Objects.equals(cost, otherShark.getCost())
                && Objects.equals(character, otherShark.getChar())
                && birthDate == otherShark.getBirthDate();
    }
}
