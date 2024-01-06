package ru.skypro.homework.service.impl;

public class MissingUserException extends RuntimeException {
    public MissingUserException(String message) {
        super(message);
    }
}
