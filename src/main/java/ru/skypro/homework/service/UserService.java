package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

/**
 * Интерфейс для управления профилем пользователя.
 * Включает смену пароля, обновление профиля, аватара и регистрацию новых пользователей.
 */
public interface UserService {

    void changePassword(NewPassword newPassword, String email);

    User getCurrentUser(String email);

    UpdateUser updateUser(UpdateUser updateUser, String email);

    void updateUserImage(MultipartFile file, String email);

}
