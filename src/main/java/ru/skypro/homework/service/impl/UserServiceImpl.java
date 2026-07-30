package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import javax.persistence.EntityNotFoundException;

/**
 * Реализация сервиса работы с профилем пользователя.
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Возвращает DTO авторизованного пользователя.
     *
     * @param username логин пользователя
     * @return информация о пользователе
     */
    @Override
    @Transactional(readOnly = true)
    public User getUser(String username) {
        UserEntity user = findUser(username);

        return userMapper.toDto(user);
    }

    /**
     * Изменяет имя, фамилию и телефон пользователя.
     *
     * @param username   логин пользователя
     * @param updateUser новые данные
     * @return обновлённые данные профиля
     */
    @Override
    @Transactional
    public UpdateUser updateUser(
            String username,
            UpdateUser updateUser
    ) {
        UserEntity user = findUser(username);

        userMapper.updateEntity(user, updateUser);

        UserEntity savedUser =
                userRepository.save(user);

        return userMapper.toUpdateUser(savedUser);
    }

    /**
     * Проверяет текущий пароль и сохраняет новый BCrypt-хеш.
     *
     * @param username    логин пользователя
     * @param newPassword текущий и новый пароли
     * @return false, если текущий пароль неверен
     */
    @Override
    @Transactional
    public boolean setPassword(
            String username,
            NewPassword newPassword
    ) {
        UserEntity user = findUser(username);

        boolean currentPasswordCorrect =
                passwordEncoder.matches(
                        newPassword.getCurrentPassword(),
                        user.getPassword()
                );

        if (!currentPasswordCorrect) {
            return false;
        }

        user.setPassword(
                passwordEncoder.encode(
                        newPassword.getNewPassword()
                )
        );

        userRepository.save(user);

        return true;
    }

    private UserEntity findUser(String username) {
        return userRepository
                .findByUsernameIgnoreCase(username)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "Пользователь "
                                        + username
                                        + " не найден"
                        )
                );
    }

    /**
     * Сохраняет изображение профиля пользователя.
     *
     * @param username логин пользователя
     * @param imageBytes байты изображения
     * @param mediaType MIME-тип изображения
     */
    @Override
    @Transactional
    public void updateImage(
            String username,
            byte[] imageBytes,
            String mediaType
    ) {
        UserEntity user = findUser(username);

        user.setImage(imageBytes);
        user.setImageMediaType(mediaType);

        userRepository.save(user);
    }

    /**
     * Возвращает байты аватара пользователя.
     *
     * @param userId идентификатор пользователя
     * @return изображение
     */
    @Override
    @Transactional(readOnly = true)
    public byte[] getImage(Long userId) {
        UserEntity user = findUserById(userId);

        if (user.getImage() == null) {
            throw new EntityNotFoundException(
                    "Аватар пользователя с id "
                            + userId
                            + " не найден"
            );
        }

        return user.getImage();
    }

    /**
     * Возвращает MIME-тип аватара.
     *
     * @param userId идентификатор пользователя
     * @return MIME-тип изображения
     */
    @Override
    @Transactional(readOnly = true)
    public String getImageMediaType(Long userId) {
        UserEntity user = findUserById(userId);

        if (user.getImageMediaType() == null) {
            return "application/octet-stream";
        }

        return user.getImageMediaType();
    }

    private UserEntity findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Пользователь с id "
                                        + userId
                                        + " не найден"
                        )
                );
    }

}
