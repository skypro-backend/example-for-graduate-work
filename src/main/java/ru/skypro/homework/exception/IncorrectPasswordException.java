package ru.skypro.homework.exception;

public class IncorrectPasswordException extends RuntimeException{
    public IncorrectPasswordException() {
    }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPasswordException(Throwable cause) {
        super(cause);
    }

    public IncorrectPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
