package ru.skypro.homework.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@Service
public class UserServiceImpl {

    /**
     * Метод установки пароля
     *
     * @param password - новый пароль
     * @return возвращает установленный пароль
     */
    public NewPassword setPassword(NewPassword password) {
        return null;
    }

    /**
     * Метод получения всех пользователей
     */
    public User getUsers() {
        return null;
    }

    /**
     * Метод изменения пользователя
     *
     * @param user - пользователь на изменение
     * @return возвращает обновленного пользователя
     */
    public User updateUser(User user) {
        return null;
    }

    /**
     * Метод обновления изображения пользователя
     */
    public ResponseEntity<Void> updateUserImage(MultipartFile image) {
        return null;
    }
}
