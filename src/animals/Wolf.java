package animals;

import java.math.BigDecimal;

public class Wolf extends Predator {
    public Wolf() {
        name = "Клык";
        breed = "Обыкновенный";
        cost = new BigDecimal(50000);
        character = "Агрессивный";
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
