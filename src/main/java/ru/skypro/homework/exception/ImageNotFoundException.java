package ru.skypro.homework.exception;

public class ImageNotFoundException extends RuntimeException{
    public ImageNotFoundException() {
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}
