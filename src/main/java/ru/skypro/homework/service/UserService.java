package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

/**
 * Сервис для управления пользователями
 * <p>
 * Предоставляет методы для получения, обновления информации о пользователях,
 * а также для работы с аватарами пользователей
 * </p>
 */
public interface UserService {

    /**
     * Получает информацию о пользователе по email
     *
     * @param email email пользователя
     * @return DTO пользователя
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException если пользователь не найден
     */
    User getUserByEmail(String email);

    /**
     * Обновляет информацию о пользователе
     *
     * @param email      email пользователя
     * @param updateUser DTO с обновленными данными
     * @return обновленный DTO пользователя
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException если пользователь не найден
     */
    User updateUser(String email, UpdateUser updateUser);

    /**
     * Обновляет аватар пользователя
     *
     * @param email email пользователя
     * @param image файл изображения для аватара
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException если пользователь не найден
     * @throws RuntimeException если не удалось сохранить изображение
     */
    void updateUserImage(String email, MultipartFile image);

    /**
     * Получает изображение аватара пользователя
     *
     * @param email email пользователя
     * @return массив байтов изображения или null, если аватар не найден
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException если пользователь не найден
     */
    byte[] getUserImage(String email);
}