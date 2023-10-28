package ru.skypro.homework.security;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;

/**
 * Интерфейс сервиса для регистрации пользователя и входа
 */
public interface AuthService {
    boolean login(String userName, String password); // Метод производит авторизацию пользователя

    boolean register (Register register); // Метод регистрирует пользователя
}
