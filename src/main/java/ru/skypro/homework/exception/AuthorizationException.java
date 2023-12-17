package ru.skypro.homework.exception;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String message) {
        super("Authorization failed");
    }
}
