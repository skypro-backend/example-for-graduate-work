package ru.skypro.homework.exception;

public class NotAllowedPasswordException extends RuntimeException {
    public NotAllowedPasswordException() {
    }

    public NotAllowedPasswordException(String message) {
        super(message);
    }

    public NotAllowedPasswordException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotAllowedPasswordException(Throwable cause) {
        super(cause);
    }

    public NotAllowedPasswordException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
