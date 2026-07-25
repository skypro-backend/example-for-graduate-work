package com.skypro.service.impl;

import com.skypro.dto.UpdateUser;
import com.skypro.dto.User;
import com.skypro.exception.InvalidPasswordException;
import com.skypro.exception.UserNotFoundException;
import com.skypro.mapper.UserMapper;
import com.skypro.repository.UserRepository;
import com.skypro.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса {@link UserService} для управления данными пользователей.
 * <p>
 * Содержит бизнес-логику:
 * <ul>
 *   <li>Получение информации о пользователе</li>
 *   <li>Обновление профиля</li>
 *   <li>Смена пароля с проверкой старого</li>
 *   <li>Обновление аватарки</li>
 * </ul>
 * </p>
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           UserMapper userMapper,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Возвращает информацию о пользователе по его имени.
     *
     * @param username логин пользователя
     * @return DTO {@link User} с данными пользователя
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username)
                .map(userMapper::toUser)
                .orElseThrow(() -> new UserNotFoundException(username));
    }

    /**
     * Обновляет данные профиля пользователя.
     *
     * @param username   логин пользователя
     * @param updateUser DTO с новыми данными (имя, фамилия, телефон)
     * @return обновлённый DTO {@link User}
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public User updateUser(String username, UpdateUser updateUser) {
        var userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        userEntity.setFirstName(updateUser.getFirstName());
        userEntity.setLastName(updateUser.getLastName());
        userEntity.setPhone(updateUser.getPhone());
        userRepository.save(userEntity);
        return userMapper.toUser(userEntity);
    }

    /**
     * Меняет пароль пользователя.
     * <p>
     * Сначала проверяет, что старый пароль введён верно.
     * Если проверка пройдена, новый пароль хешируется и сохраняется.
     * </p>
     *
     * @param username        логин пользователя
     * @param currentPassword текущий пароль (в открытом виде)
     * @param newPassword     новый пароль (в открытом виде)
     * @throws UserNotFoundException   если пользователь не найден
     * @throws InvalidPasswordException если текущий пароль не совпадает
     */
    @Override
    public void changePassword(String username, String currentPassword, String newPassword) {
        var userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        if (!passwordEncoder.matches(currentPassword, userEntity.getPassword())) {
            throw new InvalidPasswordException();
        }
        userEntity.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(userEntity);
    }

    /**
     * Обновляет аватарку пользователя.
     *
     * @param username логин пользователя
     * @param filename имя файла аватарки (сохранённого в папке uploads/users/)
     * @throws UserNotFoundException если пользователь не найден
     */
    @Override
    public void updateAvatar(String username, String filename) {
        var userEntity = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        userEntity.setImage(filename);
        userRepository.save(userEntity);
    }
}
