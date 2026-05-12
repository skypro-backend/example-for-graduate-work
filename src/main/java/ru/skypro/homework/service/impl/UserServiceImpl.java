package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.controller.UserController;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.EntityNotFoundException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

/**
 * Реализация сервиса для управления данными пользователей.
 * <p>
 * Обеспечивает бизнес-логику:
 * <ul>
 *   <li>Получение профиля пользователя по email</li>
 *   <li>Обновление имени, фамилии и телефона</li>
 *   <li>Смена пароля с проверкой текущего</li>
 *   <li>Загрузка нового аватара</li>
 * </ul>
 * Все операции требуют аутентификации. Проверка прав выполняется через Spring Security.
 * <p>
 * Интегрируется с:
 * <ul>
 *   <li>{@link UserRepository} — для доступа к данным пользователей</li>
 *   <li>{@link UserMapper} — для преобразования сущности в DTO</li>
 *   <li>{@link PasswordEncoder} — для безопасного хеширования паролей</li>
 *   <li>{@link ImageService} — для сохранения изображений аватаров</li>
 * </ul>
 *
 * @see UserService — основной интерфейс, используемый контроллерами
 * @see UserController — вызывает методы этого сервиса при работе с профилем
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * Репозиторий для доступа к данным пользователей.
     */
    private final UserRepository userRepository;

    /**
     * Маппер для преобразования сущности {@link UserEntity} в DTO {@link User}.
     */
    private final UserMapper userMapper;

    /**
     * Кодировщик паролей, использующий алгоритм BCrypt.
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Сервис для работы с изображениями (сохранение аватаров).
     */
    private final ImageService imageService;

    /**
     * Получает данные пользователя по его email.
     * <p>
     * Используется при открытии страницы профиля.
     *
     * @param email email пользователя (используется как логин)
     * @return объект {@link User} с основными данными: имя, фамилия, телефон, роль, ссылка на аватар
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public User getUser(String email) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return userMapper.toDto(user);
    }

    /**
     * Частично обновляет профиль пользователя.
     * <p>
     * Обновляет имя, фамилию и телефон. Все изменения сохраняются в базе данных.
     *
     * @param email      email текущего пользователя
     * @param updateUser DTO с новыми значениями полей
     * @return обновлённый объект {@link User}
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    @Transactional
    public User updateUser(String email, UpdateUser updateUser) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        user.setFirstName(updateUser.getFirstName());
        user.setLastName(updateUser.getLastName());
        user.setPhone(updateUser.getPhone());

        UserEntity saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    /**
     * Изменяет пароль пользователя после проверки текущего.
     * <p>
     * Если текущий пароль неверен — выбрасывается исключение.
     *
     * @param email       email пользователя
     * @param newPassword DTO с полями: текущий пароль и новый пароль
     * @throws BadCredentialsException если текущий пароль не совпадает
     */
    @Override
    @Transactional
    public void updatePassword(String email, NewPassword newPassword) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));

        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid current password");
        }

        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
    }

    /**
     * Обновляет аватар пользователя.
     * <p>
     * Сохраняет изображение через {@link ImageService} с именем {@code user_{id}.jpg}.
     *
     * @param email email пользователя
     * @param image байты нового изображения
     * @throws EntityNotFoundException если пользователь не найден
     * @throws RuntimeException        если произошла ошибка при сохранении файла
     */
    @Override
    public void updateUserImage(String email, byte[] image) {
        UserEntity user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException("Пользователь не найден"));
        try {
            imageService.saveUserImage(user.getId(), image);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка сохранения изображения", e);
        }
    }

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
    @Override
    public void changePassword(String currentPassword, String newPassword, Authentication authentication) {
        UserEntity user = (UserEntity) authentication.getPrincipal();

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            throw new BadCredentialsException("Current password is incorrect");
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }
}