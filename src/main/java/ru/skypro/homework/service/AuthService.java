package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Интерфейс сервиса аутентификации и регистрации.
 * Определяет методы для входа в систему и создания новых пользователей.
 * Основные функции:
 * - авторизация пользователя по логину и паролю;
 * - регистрация нового пользователя.
 */
public interface AuthService {

    /**
     * Авторизация пользователя.
     * @param userName логин пользователя (email)
     * @param password пароль пользователя
     * @return true если авторизация успешна, иначе false
     */
    boolean login(String userName, String password);

    /**
     * Регистрация нового пользователя.
     * @param register DTO с данными для регистрации
     * @return true если регистрация успешна, иначе false
     */
    boolean register(Register register);
}
