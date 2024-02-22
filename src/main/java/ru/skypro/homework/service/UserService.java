package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

/**
 * сервис пользователя
 */
public interface UserService {
    /**
     * получить пользователя
     */
    User getUser(Authentication authentication);

    /**
     * обновить пользователя
     */
    UpdateUser updateUser(UpdateUser updateUserDto, Authentication authentication);
}
