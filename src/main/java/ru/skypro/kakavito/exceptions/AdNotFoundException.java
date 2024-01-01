package ru.skypro.kakavito.exceptions;

public class AdNotFoundException extends RuntimeException{
    public AdNotFoundException(String message) {
        super(message);
    }
}
