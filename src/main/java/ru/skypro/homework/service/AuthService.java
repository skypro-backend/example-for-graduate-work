package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

public interface AuthService {

    /**
     * Авторизация пользователя по логину и паролю
     *
     * @param username {@link String}
     * @param password {@link String}
     * @return boolean
     */
    boolean login(String username, String password);

    /**
     * Регистрация пользователя по логину и паролю
     *
     * @param register {@link Register}
     * @return boolean
     */
    boolean register(Register register);
}
