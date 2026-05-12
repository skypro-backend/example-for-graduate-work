package ru.skypro.homework.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.IOException;

/**
 * Сервис для управления профилем пользователя.
 * <p>
 * Определяет контракт бизнес-логики, связанной с:
 * <ul>
 *   <li>Получением данных текущего пользователя</li>
 *   <li>Обновлением имени, фамилии и телефона</li>
 *   <li>Сменой пароля с проверкой текущего</li>
 *   <li>Загрузкой нового аватара</li>
 * </ul>
 * Все операции требуют аутентификации. Проверка прав доступа выполняется внутри реализации.
 * <p>
 * Интегрируется с контроллером {@link ru.skypro.homework.controller.UserController},
 * который использует этот сервис для обработки HTTP-запросов к /users/me.
 *
 * @see UserServiceImpl — основная реализация интерфейса
 * @see UserController — использует методы этого сервиса при работе с профилем
 * @see User — DTO для передачи данных о пользователе
 * @see UpdateUser — DTO для обновления профиля
 * @see NewPassword — DTO для смены пароля
 */
public interface UserService {

    /**
     * Получает данные пользователя по его email.
     * <p>
     * Возвращает полную информацию: имя, фамилия, телефон, роль, ссылку на аватар.
     * Используется при открытии страницы профиля.
     *
     * @param email email пользователя (используется как логин)
     * @return объект {@link User} с основными данными
     * @throws UsernameNotFoundException если пользователь не найден
     */
    User getUser(String email);

    /**
     * Частично обновляет профиль пользователя.
     * <p>
     * Обновляет имя, фамилию и телефон. Все поля проходят валидацию.
     *
     * @param email      email текущего пользователя
     * @param updateUser DTO с новыми значениями полей
     * @return обновлённый объект {@link User}
     * @throws UsernameNotFoundException если пользователь не найден
     */
    User updateUser(String email, UpdateUser updateUser);

    /**
     * Изменяет пароль пользователя после проверки текущего.
     * <p>
     * Если текущий пароль неверен — выбрасывается исключение.
     *
     * @param email       email пользователя
     * @param newPassword DTO с полями: текущий пароль и новый пароль
     * @throws BadCredentialsException если текущий пароль не совпадает
     */
    void updatePassword(String email, NewPassword newPassword);

    /**
     * Обновляет аватар текущего пользователя.
     * <p>
     * Сохраняет изображение через {@link ImageService} с именем {@code user_{id}.jpg}.
     *
     * @param email email пользователя
     * @param image байты нового изображения
     * @throws EntityNotFoundException если пользователь не найден
     * @throws RuntimeException        если произошла ошибка при сохранении файла
     */
    void updateUserImage(String email, byte[] image);

    /**
     * Устаревший метод смены пароля — используется напрямую с объектом Authentication.
     * <p>
     * Оставлен для обратной совместимости, хотя основной поток использует {@link #updatePassword(String, NewPassword)}.
     *
     * @param currentPassword текущий пароль
     * @param newPassword     новый пароль
     * @param authentication  текущий аутентифицированный пользователь
     * @throws BadCredentialsException если текущий пароль неверен
     */
    void changePassword(String currentPassword, String newPassword, Authentication authentication);
}