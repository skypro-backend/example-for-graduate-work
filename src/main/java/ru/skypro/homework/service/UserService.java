package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

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

    /**
     * установить новый пароль пользователя
     */
    void setPassword(NewPassword newPassword, Authentication authentication);

    /**
     * найти пользователя по email - логину*
     */
    UserEntity findEntityByEmail(String email);

    /**
     * найти пользователя по id
     */
    UserEntity findById(long id);

}
