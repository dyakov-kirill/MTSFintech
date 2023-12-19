package animals;

import java.math.BigDecimal;

public class Shark extends Predator {
    public Shark() {
        name = "Челюсть";
        breed = "Белая акула";
        cost = new BigDecimal(100000);
        character = "Злая";
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
}
