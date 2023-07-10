package ru.skypro.homework.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException() {
        super("Такого пользователя не существует");
    }
}
