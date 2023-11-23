package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

/**
 * Сервис аутентификации/авторизации
 */
public interface AuthService {

    /**
     * Аутентификация
     *
     * @param userName Логин
     * @param password Пароль
     * @return Успешная аутентификация или нет
     */
    boolean login(String userName, String password);

    /**
     * Регистрация
     *
     * @param register ДТО пользователя для регистрации
     * @return Успешная регистрация или нет
     */
    boolean register(Register register);

    /**
     * Проверка пароля
     *
     * @param source Входной пароль
     * @param target Целевой пароль (с которым идет сравнение)
     * @return Пароли совпадают или нет
     */
    boolean checkPasswords(String source, String target);

}
