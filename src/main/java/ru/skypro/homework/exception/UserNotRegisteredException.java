package ru.skypro.homework.exception;

public class UserNotRegisteredException extends RuntimeException {
    public UserNotRegisteredException() {
    }

    public UserNotRegisteredException(String message) {
        super(message);
    }
}
