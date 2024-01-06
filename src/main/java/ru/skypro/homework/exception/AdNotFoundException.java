package ru.skypro.homework.exception;

public class AdNotFoundException extends RuntimeException{
    public AdNotFoundException() {
    }

    public AdNotFoundException(String message) {
        super(message);
    }

    public AdNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public AdNotFoundException(Throwable cause) {
        super(cause);
    }

    public AdNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
