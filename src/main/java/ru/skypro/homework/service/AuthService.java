package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * AuthService интерфейс
 * <br>
 * <br>- login;
 * <br>- register;
 */
public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
