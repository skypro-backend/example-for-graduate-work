package ru.skypro.kakavito.exceptions;

public class EmptyException extends RuntimeException{

    public EmptyException(String message) {
        super(message);
    }
}