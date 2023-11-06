package ru.skypro.homework.exceptions;

public class AdNotFoundException extends RuntimeException{
    public AdNotFoundException() {
        super();
    }

    public AdNotFoundException(String message) {
        super(message);
    }
}
