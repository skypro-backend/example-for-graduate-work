package com.skypro.service;

import com.skypro.dto.RegisterReq;

/**
 * Сервис для аутентификации и регистрации пользователей.
 */
public interface AuthService {

    /**
     * Выполняет проверку учётных данных пользователя.
     *
     * @param userName логин
     * @param password пароль (в открытом виде)
     * @return {@code true}, если логин и пароль совпадают, иначе {@code false}
     */
    boolean login(String userName, String password);

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param registerReq данные для регистрации
     * @return {@code true}, если регистрация прошла успешно,
     *         {@code false}, если пользователь с таким логином уже существует
     */
    boolean register(RegisterReq registerReq);
}