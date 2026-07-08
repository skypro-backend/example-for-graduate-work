package ru.skypro.homework.service;

import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;

    //* Сервис регистрации новых пользователей.

public interface AuthService {

    //* Регистрирует нового пользователя в системе.
    //* @param register данные для регистрации (логин, пароль, ФИО)
    //* @return ID созданной сущности UserEntity

    Integer register(Register register);
    Boolean login(String userName, String password);
}