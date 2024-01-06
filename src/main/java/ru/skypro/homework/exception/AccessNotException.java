package ru.skypro.homework.exception;

public class AccessNotException extends RuntimeException{
    public AccessNotException() {
    }

    public AccessNotException(String message) {
        super(message);
    }

    public AccessNotException(String message, Throwable cause) {
        super(message, cause);
    }

    public AccessNotException(Throwable cause) {
        super(cause);
    }

    public AccessNotException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
