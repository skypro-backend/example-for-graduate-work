package ru.skypro.homework.service;

import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;

/**
 * Сервис регистрации и авторизации пользователей.
 */
public interface AuthService {

    /**
     * Регистрирует нового пользователя.
     *
     * @param register данные нового пользователя
     * @return true, если пользователь успешно создан;
     * false, если такой username уже существует
     */
    boolean register(Register register);

    /**
     * Проверяет логин и пароль пользователя.
     *
     * @param login данные для входа
     * @return true, если данные пользователя корректны
     */
    boolean login(Login login);
}
