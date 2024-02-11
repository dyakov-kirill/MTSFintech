package ru.mtsbank.animals;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Cat extends Pet {
    public Cat(String name, String breed, BigDecimal cost, String character, LocalDate birthDate) {
        this.name = name;
        this.breed = breed;
        this.cost = cost;
        this.character = character;
        this.birthDate = birthDate;
    }

    public Cat() {
        name = "Барсик";
        breed = "Британская";
        cost = new BigDecimal(10000);
        character = "Ласковый";
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
        if (obj == null || obj.getClass() != Cat.class) {
            return false;
        }
        Cat otherCat = (Cat) obj;
        return Objects.equals(breed, otherCat.getBreed())
                && Objects.equals(name, otherCat.getName())
                && Objects.equals(cost, otherCat.getCost())
                && Objects.equals(character, otherCat.getChar())
                && Objects.equals(birthDate, otherCat.getBirthDate());
    }
}
