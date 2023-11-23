package ru.skypro.homework.security;

import org.springframework.security.core.Authentication;

/**
 * Предоставление информации по текущему пользователю (в security context-e)
 */
public interface AuthProvider {

    /**
     * Получение объекта аутентификации
     *
     * @return Объект аутентификации
     */
    Authentication getAuthentication();

    /**
     * Получение логина
     *
     * @return Логин
     */
    String getUsername();

}
