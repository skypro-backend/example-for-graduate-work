package ru.skypro.homework.exception;


public class PasswordsNotMatchException extends RuntimeException{
    public PasswordsNotMatchException() {
    }

    public PasswordsNotMatchException(String message) {
        super(message);
    }

    public PasswordsNotMatchException(String message, Throwable cause) {
        super(message, cause);
    }

    public PasswordsNotMatchException(Throwable cause) {
        super(cause);
    }

    public PasswordsNotMatchException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
