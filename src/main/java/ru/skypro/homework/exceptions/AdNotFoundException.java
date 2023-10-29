package ru.skypro.homework.exceptions;

public class AdNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Ad is not found";

    public AdNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
