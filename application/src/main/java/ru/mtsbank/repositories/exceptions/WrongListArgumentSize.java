package ru.mtsbank.repositories.exceptions;

public class WrongListSizeArgument extends Exception {
    public WrongListSizeArgument() {
    }

    public WrongListSizeArgument(String message) {
        super(message);
    }

    public WrongListSizeArgument(String message, Throwable cause) {
        super(message, cause);
    }

    public WrongListSizeArgument(Throwable cause) {
        super(cause);
    }

    public WrongListSizeArgument(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
