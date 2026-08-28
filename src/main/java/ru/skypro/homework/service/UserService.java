package ru.skypro.homework.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;
import org.springframework.stereotype.Service;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.ImageEntity;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.io.IOException;
import java.util.Optional;

/**
 * Сервис для управления данными пользователя.
 * Содержит бизнес-логику по работе с профилем пользователя.
 * Основные функции:
 * - получение пользователя по ID или email;
 * - обновление данных пользователя;
 * - смена пароля с проверкой старого пароля;
 * - проверка существования пользователя по email;
 * - обновление аватара пользователя.
 */
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final ImageService imageService;

    /**
     * Получить пользователя по ID.
     * @param id ID пользователя
     * @return Optional с DTO пользователя
     */
    public Optional<User> getUserById(Integer id) {
        return userRepository.findById(id)
                .flatMap(userMapper::toDto);
    }

    /**
     * Получить пользователя по email.
     * @param email email пользователя
     * @return Optional с DTO пользователя
     */
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .flatMap(userMapper::toDto);
    }

    /**
     * Обновить данные пользователя (имя, фамилия, телефон, аватар).
     * @param userDto DTO с обновлёнными данными
     * @return Optional с обновлённым DTO пользователя
     */
    public Optional<User> updateUser(User userDto) {
        return userRepository.findById(userDto.getId())
                .map(entity -> {
                    entity.setFirstName(userDto.getFirstName());
                    entity.setLastName(userDto.getLastName());
                    entity.setPhone(userDto.getPhone());
                    return userRepository.save(entity);
                })
                .flatMap(userMapper::toDto);
    }

    /**
     * Сменить пароль пользователя.
     * Проверяет совпадение старого пароля перед установкой нового.
     * @param email       email пользователя
     * @param oldPassword старый пароль
     * @param newPassword новый пароль
     * @return true если пароль успешно изменён, иначе false
     */
    public boolean changePassword(String email, String oldPassword, String newPassword) {
        return userRepository.findByEmail(email)
                .map(user -> {
                    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
                        return false;
                    }
                    user.setPassword(passwordEncoder.encode(newPassword));
                    userRepository.save(user);
                    return true;
                })
                .orElse(false);
    }

    /**
     * Проверить, существует ли пользователь с указанным email.
     * @param email email пользователя
     * @return true если пользователь существует, иначе false
     */
    public boolean existsByEmail(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    /**
     * Обновить аватар пользователя.
     * Сохраняет новое изображение, удаляет старый аватар и обновляет ссылку у пользователя.
     * @param userId ID пользователя
     * @param file   новый файл аватара
     * @throws IOException если ошибка при сохранении файла
     */
    public void updateUserAvatar(Integer userId, MultipartFile file) throws IOException {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        ImageEntity avatar = imageService.saveImage(file);

        if (user.getAvatar() != null) {
            imageService.deleteImage(user.getAvatar().getId());
        }

        user.setAvatar(avatar);
        userRepository.save(user);
    }

    /**
     * Получить сущность пользователя по email.
     * Используется для внутренних операций, где требуется работа с сущностью.
     * @param email email пользователя
     * @return сущность пользователя
     * @throws RuntimeException если пользователь не найден
     */
    public UserEntity getUserEntityByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}