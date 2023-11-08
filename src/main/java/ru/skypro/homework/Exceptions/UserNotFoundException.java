package ru.skypro.homework.Exceptions;

public class UserNotFoundException extends NotFoundException {

    public UserNotFoundException() {
        super("Пользователь не найден");
    }
}