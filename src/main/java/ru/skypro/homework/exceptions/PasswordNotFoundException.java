package ru.skypro.homework.exceptions;

public class PasswordNotFoundException extends RuntimeException{
    public PasswordNotFoundException() {
    }

    public PasswordNotFoundException(String message) {
        super(message);
    }
}
