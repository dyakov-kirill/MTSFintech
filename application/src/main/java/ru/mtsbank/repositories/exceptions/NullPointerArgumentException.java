package ru.mtsbank.repositories.exceptions;

public class NullPointerArgumentException extends IllegalArgumentException {
    public NullPointerArgumentException() {
    }

    public NullPointerArgumentException(String s) {
        super(s);
    }

    public NullPointerArgumentException(String message, Throwable cause) {
        super(message, cause);
    }

    public NullPointerArgumentException(Throwable cause) {
        super(cause);
    }
}
