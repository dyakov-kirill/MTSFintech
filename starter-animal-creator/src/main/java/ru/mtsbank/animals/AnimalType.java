package ru.mtsbank.animals;

public enum AnimalType {
    CAT("Cat"),
    DOG("Dog"),
    SHARK("Shark"),
    WOLF("Wolf");

    private final String type;

    AnimalType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }
}
