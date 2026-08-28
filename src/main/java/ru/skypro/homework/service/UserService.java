package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * Сервис для управления пользователями.
 * Предоставляет методы для работы с данными пользователей,
 * включая получение, обновление информации и смену пароля.
 */
public interface UserService {

    /**
     * Получает информацию о пользователе по его имени.
     *
     * @param username имя пользователя
     * @return DTO с информацией о пользователе
     * @throws RuntimeException если пользователь не найден
     */
    User getUser(String username);

    /**
     * Обновляет информацию о пользователе.
     *
     * @param username имя пользователя
     * @param updateUser DTO с обновленными данными
     * @return DTO с обновленной информацией
     * @throws RuntimeException если пользователь не найден
     */
    UpdateUser updateUser(String username, UpdateUser updateUser);

    /**
     * Изменяет пароль пользователя.
     *
     * @param username имя пользователя
     * @param newPassword DTO с текущим и новым паролем
     * @return true, если пароль успешно изменен
     * @throws IllegalArgumentException если текущий пароль неверен
     * @throws RuntimeException если пользователь не найден
     */
    boolean setPassword(String username, NewPassword newPassword);

    /**
     * Обновляет аватар пользователя.
     *
     * @param username имя пользователя
     * @param image файл с изображением
     * @return URL загруженного изображения
     * @throws RuntimeException если пользователь не найден или ошибка сохранения
     */
    String updateUserImage(String username, MultipartFile image);
}
