package ru.skypro.homework.exceptions;
public class MissingImageException extends RuntimeException {
    public MissingImageException(String message) {
        super(message);
    }
}
