package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

/**
 * Сервис работы с профилем пользователя.
 */
public interface UserService {

    /**
     * Возвращает информацию об авторизованном пользователе.
     *
     * @param username логин авторизованного пользователя
     * @return информация о пользователе
     */
    User getUser(String username);

    /**
     * Обновляет профиль авторизованного пользователя.
     *
     * @param username   логин пользователя
     * @param updateUser новые данные профиля
     * @return обновлённые данные
     */
    UpdateUser updateUser(
            String username,
            UpdateUser updateUser
    );

    /**
     * Изменяет пароль пользователя.
     *
     * @param username    логин пользователя
     * @param newPassword текущий и новый пароли
     * @return true, если текущий пароль указан правильно
     */
    boolean setPassword(
            String username,
            NewPassword newPassword
    );

    /**
     * Сохраняет аватар авторизованного пользователя.
     *
     * @param username логин пользователя
     * @param imageBytes содержимое изображения
     * @param mediaType MIME-тип изображения
     */
    void updateImage(
            String username,
            byte[] imageBytes,
            String mediaType
    );

    /**
     * Возвращает аватар пользователя.
     *
     * @param userId идентификатор пользователя
     * @return содержимое изображения
     */
    byte[] getImage(Long userId);

    /**
     * Возвращает MIME-тип аватара.
     *
     * @param userId идентификатор пользователя
     * @return MIME-тип изображения
     */
    String getImageMediaType(Long userId);

}
