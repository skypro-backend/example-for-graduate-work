package com.skypro.service;

import com.skypro.dto.UpdateUser;
import com.skypro.dto.User;

/**
 * Сервис для управления данными пользователей.
 */
public interface UserService {

    /**
     * Возвращает информацию о пользователе по логину.
     *
     * @param username логин пользователя
     * @return DTO {@link User}
     */
    User getUserByUsername(String username);

    /**
     * Обновляет данные профиля пользователя.
     *
     * @param username   логин пользователя
     * @param updateUser новые данные (имя, фамилия, телефон)
     * @return обновлённый DTO {@link User}
     */
    User updateUser(String username, UpdateUser updateUser);

    /**
     * Меняет пароль пользователя.
     * <p>
     * Проверяет, что старый пароль введён верно.
     * </p>
     *
     * @param username        логин пользователя
     * @param currentPassword текущий пароль
     * @param newPassword     новый пароль
     */
    void changePassword(String username, String currentPassword, String newPassword);

    /**
     * Обновляет аватарку пользователя.
     *
     * @param username логин пользователя
     * @param filename имя файла аватарки
     */
    void updateAvatar(String username, String filename);
}