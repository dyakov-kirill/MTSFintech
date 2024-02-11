package ru.mtsbank.animals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Wolf extends Predator {
    public Wolf(String name, String breed, BigDecimal cost, String character, LocalDate birthDate) {
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
    }

    public Wolf() {
        name = "Клык";
        breed = "Обыкновенный";
        cost = new BigDecimal(50000);
        character = "Агрессивный";
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
        if (obj == null || obj.getClass() != Wolf.class) {
            return false;
        }
        Wolf otherWolf = (Wolf) obj;
        return Objects.equals(breed, otherWolf.getBreed())
                && Objects.equals(name, otherWolf.getName())
                && Objects.equals(cost, otherWolf.getCost())
                && Objects.equals(character, otherWolf.getChar())
                && Objects.equals(birthDate, otherWolf.getBirthDate());
    }
}
