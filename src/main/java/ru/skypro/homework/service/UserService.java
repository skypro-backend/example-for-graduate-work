package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import org.springframework.web.multipart.MultipartFile;

/**
 * Интерфейс бизнес-логики для управления профилями пользователей.
 */
public interface UserService {
    /**
     * Изменяет текущий пароль пользователя на новый после верификации старого пароля.
     *
     * @param newPassword объект с текущим и новым паролем
     * @param username имя пользователя (email), который меняет пароль
     * @return {@code true}, если пароль успешно изменен, иначе {@code false} (например, при несовпадении старого пароля)
     */
    boolean setPassword(NewPassword newPassword, String username);

    /**
     * Возвращает профиль пользователя по его имени (email).
     *
     * @param username имя пользователя (email)
     * @return DTO {@link User} с публичной информацией профиля
     */
    User getUser(String username);

    /**
     * Обновляет контактные данные в профиле пользователя (имя, фамилия, телефон).
     *
     * @param updateUser новые данные для обновления
     * @param username имя пользователя (email)
     * @return DTO {@link UpdateUser} с обновленными данными
     */
    UpdateUser updateUser(UpdateUser updateUser, String username);

    /**
     * Сохраняет и привязывает новое изображение аватара к профилю пользователя.
     *
     * @param image файл изображения аватара
     * @param username имя пользователя (email)
     */
    void updateUserImage(MultipartFile image, String username);
}