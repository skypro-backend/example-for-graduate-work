package ru.skypro.homework.exception;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        super("Access denied");
    }
}
