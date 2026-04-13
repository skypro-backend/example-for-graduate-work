package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.User;

import java.util.Optional;

/**
 * Сервис для аутентификации и регистрации пользователей
 * <p>
 * Предоставляет методы для входа, регистрации и управления паролями
 * </p>
 */
public interface AuthService {

    /**
     * Выполняет аутентификацию пользователя
     *
     * @param email    email пользователя
     * @param password пароль в открытом виде
     * @return true если учетные данные верны, false в противном случае
     */
    boolean login(String email, String password);

    /**
     * Регистрирует нового пользователя в системе
     *
     * @param register DTO с данными для регистрации
     * @return true если регистрация успешна, false если email уже существует
     */
    boolean register(Register register);

    /**
     * Изменяет пароль пользователя
     *
     * @param email           email пользователя
     * @param currentPassword текущий пароль
     * @param newPassword     новый пароль
     * @return true если пароль успешно изменен, false если текущий пароль неверен
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException если пользователь не найден
     */
    boolean changePassword(String email, String currentPassword, String newPassword);

    /**
     * Получает информацию о пользователе по email
     *
     * @param email email пользователя
     * @return Optional с DTO пользователя или пустой Optional
     */
    Optional<User> getUserByEmail(String email);
}