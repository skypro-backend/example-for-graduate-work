package ru.skypro.homework.exceptions;

public class BlankFieldException extends RuntimeException {
    public BlankFieldException() {
    }
    public BlankFieldException(String message) {
        super(message);
    }
}
