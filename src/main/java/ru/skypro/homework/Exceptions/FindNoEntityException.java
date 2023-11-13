package ru.skypro.homework.Exceptions;

public class FindNoEntityException extends RuntimeException{

    public FindNoEntityException(String message) {
        super(message);
    }
}

