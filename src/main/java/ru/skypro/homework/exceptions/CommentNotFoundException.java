package ru.skypro.homework.exceptions;

public class CommentNotFoundException extends RuntimeException {

    public static final String DEFAULT_MESSAGE = "Комментарий не найден";

    public CommentNotFoundException() {
        super(DEFAULT_MESSAGE);
    }
}
