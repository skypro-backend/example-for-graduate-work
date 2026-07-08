package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.user.NewPassword;
import ru.skypro.homework.user.UpdateUser;
import ru.skypro.homework.user.User;
import ru.skypro.homework.dto.Register;
import org.springframework.security.core.Authentication;

    //      * Сервис для управления профилем текущего пользователя.

public interface UserService {

    //     * Получает данные профиля текущего авторизованного пользователя.
    //     * @param auth объект аутентификации
    //     * @return DTO объекта User

    User getCurrentUser(Authentication auth);

    //     * Обновляет персональные данные профиля (имя, фамилия, телефон).
    //     * @param data новые данные профиля
    //     * @param auth объект аутентификации
    //     * @return обновленный DTO объекта User

    User updateProfile(UpdateUser data, Authentication auth);

    //     * Меняет пароль текущего пользователя после проверки старого пароля.
    //     * @param data старый и новый пароли
    //     * @param auth объект аутентификации
    //     * @return true при успешной смене

    Boolean changePassword(NewPassword data, Authentication auth);

    //     * Загружает аватар пользователя в файловую систему.
    //     * @param image файл изображения
    //     * @param auth объект аутентификации

    Integer register(Register data);

    void uploadAvatar(MultipartFile image, Authentication auth);
}