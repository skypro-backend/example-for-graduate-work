package ru.skypro.homework.exception;

public class CommentNotFoundException extends RuntimeException{
    public CommentNotFoundException() {
    }

    public CommentNotFoundException(String message) {
        super(message);
    }
}
