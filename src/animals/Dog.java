package animals;

import java.math.BigDecimal;

public class Dog extends Pet{
    public Dog() {
        name = "Дружок";
        breed = "Немецкая овчарка";
        cost = new BigDecimal(30000);
        character = "Верный";
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
