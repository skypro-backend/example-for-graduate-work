package ru.skypro.homework.exception;


public class UserAlreadyAddException extends RuntimeException{
    public UserAlreadyAddException() {
    }

    public UserAlreadyAddException(String message) {
        super(message);
    }

    public UserAlreadyAddException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyAddException(Throwable cause) {
        super(cause);
    }

    public UserAlreadyAddException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
