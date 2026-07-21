package ru.skypro.homework.exception;

import org.springframework.security.access.AccessDeniedException;

public class ForbiddenException extends AccessDeniedException {

    public ForbiddenException(String msg) {
        super(msg);
    }
}