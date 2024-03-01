package ru.skypro.homework.exception;

public class NotEnoughPermissionsException extends RuntimeException {
    public NotEnoughPermissionsException() {
    }

    public NotEnoughPermissionsException(String message) {
        super(message);
    }

    public NotEnoughPermissionsException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotEnoughPermissionsException(Throwable cause) {
        super(cause);
    }
}
