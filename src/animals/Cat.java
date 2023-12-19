package animals;

import java.math.BigDecimal;

public class Cat extends Pet {
    public Cat() {
        name = "Барсик";
        breed = "Британская";
        cost = new BigDecimal(10000);
        character = "Ласковый";
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
