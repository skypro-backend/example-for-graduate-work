package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Сервис для аутентификации и регистрации пользователей.
 * Предоставляет методы для входа в систему и создания новых учетных записей.
 */
public interface AuthService {

    /**
     * Выполняет аутентификацию пользователя.
     *
     * @param username имя пользователя
     * @param password пароль пользователя
     * @return true, если аутентификация успешна
     */
    boolean login(String username, String password);

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param register DTO с данными для регистрации
     * @return true, если регистрация успешна
     */
    boolean register(Register register);
}