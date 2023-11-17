package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.models.UserEntity;

public interface UserService {

    /**
     * Метод обновляет пароль для пользователя.
     * @param oldPass старый пароль
     * @param newPass новый пароль
     */
    void setNewPassword(NewPassword newPass);

    /**
     * Метод возвращает информацию об авторизованном пользователе.
     * @return объект UserEntity, содержащий информацию о пользователе.
     */
    UserEntity getUserInfo();
}
