package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.Role;
/** Сервис для регистрации */
public interface AuthService {
    /** Логин и пароль пользователя */
    boolean login(String userName, String password);
    /** регистрация и роль пользователя */
    boolean register(RegisterReq registerReq, Role role);
}
