package ru.skypro.kakavito.exceptions;

public class UserNotAuthorizedException extends RuntimeException{
    public UserNotAuthorizedException(String message) {
        super(message);
    }
}
