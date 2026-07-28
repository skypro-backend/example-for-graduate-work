package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

/**
 * Сервис для работы с профилем авторизованного пользователя.
 */

public interface UserService {

    /**
     * Возвращает профиль текущего авторизованного пользователя.
     */

    User getUser(Authentication authentication);

    /**
     * Частично обновляет профиль текущего пользователя.
     */

    UpdateUser updateUser(UpdateUser dto, Authentication authentication);

    /**
     * Сохраняет новый аватар текущего пользователя.
     */

    void updateUserImage(MultipartFile image, Authentication authentication);

    /**
     * Меняет пароль текущего пользователя после проверки старого.
     */

    void setPassword(NewPassword dto, Authentication authentication);
}
