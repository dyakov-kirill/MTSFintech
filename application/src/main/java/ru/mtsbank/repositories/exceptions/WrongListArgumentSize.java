package ru.mtsbank.repositories.exceptions;

public class WrongListArgumentSize extends Exception {
    public WrongListArgumentSize() {
    }

    public WrongListArgumentSize(String message) {
        super(message);
    }

    public WrongListArgumentSize(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongListArgumentSize(Throwable cause) {
        super(cause);
    }

    public WrongListArgumentSize(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
