package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

/**
 * Сервис для управления профилями пользователей и их безопасностью.
 * Обеспечивает бизнес-логику получения данных, обновления профиля, смены пароля и аватара.
 */
public interface UserService {

    /**
     * Изменяет текущий пароль пользователя на новый с предварительной проверкой старого пароля.
     *
     * @param newPassword объект {@link NewPassword}, содержащий старый и новый пароли
     * @param username    логин (email) пользователя, меняющего пароль
     */
    void setPassword(NewPassword newPassword, String username);

    /**
     * Получает полную информацию о профиле текущего авторизованного пользователя.
     *
     * @param username логин (email) текущего пользователя
     * @return DTO с информацией о пользователе {@link UserDto}
     */
    UserDto getUser(String username);

    /**
     * Обновляет контактные данные (имя, фамилию, телефон) текущего пользователя.
     *
     * @param updateUser новые данные профиля {@link UpdateUser}
     * @param username   логин (email) обновляемого пользователя
     * @return DTO с обновленными данными {@link UpdateUser}
     */
    UpdateUser updateUser(UpdateUser updateUser, String username);

    /**
     * Обновляет или загружает аватар (изображение профиля) для текущего пользователя.
     *
     * @param image    файл нового изображения аватара
     * @param username логин (email) пользователя
     */
    void updateUserImage(MultipartFile image, String username);
}
