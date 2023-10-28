package ru.skypro.homework.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Comment is not found";

    public CommentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
