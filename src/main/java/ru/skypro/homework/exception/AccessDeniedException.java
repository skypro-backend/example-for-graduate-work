package ru.skypro.homework.exception;

public class AccessDeniedException extends org.springframework.security.access.AccessDeniedException {
    public AccessDeniedException(String message) {
        super(message);
    }
}