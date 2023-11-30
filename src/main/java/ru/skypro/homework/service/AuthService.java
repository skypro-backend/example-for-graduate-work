package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Интерфейс, содержащий методы для регистрации и аутентификации пользователя {@link ru.skypro.homework.model.UserInfo}
 * @see ru.skypro.homework.service.impl.AuthServiceImpl
 */
public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
