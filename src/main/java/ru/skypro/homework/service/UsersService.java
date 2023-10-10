package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UsersService {

    /**
     * Изменение пароля авторизованного пользователя
     *
     * @param currentPassword {@link String}
     * @param newPassword     {@link String}
     * @param username        {@link String}
     */
    void setPassword(String currentPassword, String newPassword, String username);

    /**
     * Получение информации об авторизованном пользователе
     *
     * @param username {@link String}
     * @return объект {@link User}
     */
    User getUser(String username);

    /**
     * Обновление информации об авторизованном пользователе
     *
     * @param updateUser {@link UpdateUser}
     * @param username   {@link String}
     * @return объект {@link UpdateUser}
     */
    UpdateUser updateUser(UpdateUser updateUser, String username);

    /**
     * Обновление аватара авторизованного пользователя
     *
     * @param file                  {@link MultipartFile}
     * @param username             {@link String}
     * @return массив байт
     */
    byte[] updateUserImage(MultipartFile file, String username);

    void createUser(UserDetails userDetails);

    boolean userExists(String username);

}
