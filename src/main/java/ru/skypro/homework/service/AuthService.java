package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

import java.util.Optional;

/**
 * Сервис аутентификации и регистрации пользователей.
 */

public interface AuthService {
    /**
     * Проверяет соответствие логина и пароля данным в БД.
     *
     * @param username логин пользователя
     * @param password пароль в открытом виде
     * @return {@code true}, если пара логин/пароль верна
     */

    boolean login(String username, String password);

    /**
     * Регистрирует нового пользователя.
     *
     * @param register данные для регистрации
     * @return id созданного пользователя, либо {@link Optional#empty()},
     * если пользователь с таким логином уже существует
     */

    Optional<Integer> register(Register register);
}
