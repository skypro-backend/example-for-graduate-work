package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Интерфейс для операций аутентификации и регистрации пользователей.
 */
public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
