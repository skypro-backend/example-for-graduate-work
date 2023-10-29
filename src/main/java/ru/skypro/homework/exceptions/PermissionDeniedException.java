package ru.skypro.homework.exceptions;

public class PermissionDeniedException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "You have no permission";

    public PermissionDeniedException() {
        super(DEFAULT_MESSAGE);
    }
}
