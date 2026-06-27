package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Интерфейс бизнес-логики для процессов аутентификации и регистрации пользователей.
 */
public interface AuthService {
    /**
     * Аутентифицирует пользователя по имени (email) и паролю.
     *
     * @param userName имя пользователя (email)
     * @param password открытый пароль, введенный пользователем
     * @return {@code true}, если аутентификация прошла успешно, иначе {@code false}
     */
    boolean login(String userName, String password);

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param register данные для регистрации (имя, пароль, роль и т.д.)
     * @return {@code true}, если регистрация завершилась успешно, иначе {@code false} (например, если логин уже занят)
     */
    boolean register(Register register);
}