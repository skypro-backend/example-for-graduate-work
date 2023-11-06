package ru.skypro.homework.Exceptions.NotFoundExpection;

public class UserNotFoundException extends NotFoundException{

    public UserNotFoundException() {
        super("Пользователь не найден");
    }
}