package ru.skypro.homework.exceptions;

public class AuthWasNotPerformedException extends RuntimeException {
    public AuthWasNotPerformedException(String message) {
        super(message);
    }
}

