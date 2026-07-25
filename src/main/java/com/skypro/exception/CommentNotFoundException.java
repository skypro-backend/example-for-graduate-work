package com.skypro.exception;

public class CommentNotFoundException extends RuntimeException {

    public CommentNotFoundException(Integer id) {
        super("Comment not found with id: " + id);
    }
}
