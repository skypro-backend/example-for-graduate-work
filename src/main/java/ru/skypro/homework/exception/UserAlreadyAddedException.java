package ru.skypro.homework.exception;

public class UserAlreadyAddedException extends RuntimeException{
    public UserAlreadyAddedException(String message){
        super(message);
    }
}
