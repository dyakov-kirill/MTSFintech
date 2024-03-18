package ru.mtsbank.repositories.exceptions;

public class NegativeArgumentException extends IllegalArgumentException {

    public NegativeArgumentException() {
    }

    public NegativeArgumentException(String s) {
        super(s);
    }

    public NegativeArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NegativeArgumentException(Throwable cause) {
        super(cause);
    }
}
