package ru.skypro.homework.security;

import ru.skypro.homework.dto.Register;

import javax.servlet.http.HttpServletRequest;

/**
 * Интерфейс сервиса для регистрации пользователя и входа
 */
public interface AuthService {
    boolean login(String userName, String password, HttpServletRequest request); // Метод производит авторизацию пользователя

    boolean register (Register register); // Метод регистрирует пользователя
}
