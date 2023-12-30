package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

public interface AuthService {

    /**
     * Метод для авторизации. При неверном логине или пароле выкидывает исключение BadCredentialsException.
     * Права имеют все пользователи
     * @param userName логин в entity это email
     * @param password пароль
     * @return true при успехе, false при неудаче
     */
    boolean login(String userName, String password);

    /**
     * Метод для регистрации. Если юсер уже есть, то выкидывает исключение UserAlreadyExistException.
     * Права имеют все пользователи
     * @param register dto для регистрации
     * @return true при успехе, false при неудаче
     */

    boolean register(Register register);
}
