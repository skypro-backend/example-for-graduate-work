package ru.skypro.homework.exceptions;

public class EmptyException extends RuntimeException{

    public EmptyException(String message) {
        super(message);
    }
}