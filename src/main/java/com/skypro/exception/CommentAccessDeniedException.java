package com.skypro.exception;

import org.springframework.security.access.AccessDeniedException;

public class CommentAccessDeniedException extends AccessDeniedException {

    public CommentAccessDeniedException(Integer id) {
        super("Access denied to comment with id: " + id);
    }
}
