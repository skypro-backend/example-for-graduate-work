package ru.skypro.homework.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

/**
 * Сервис для работы с пользователями.
 * Обеспечивает получение, обновление данных пользователя,
 * изменение пароля и обновление изображения.
 */
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository,
                       UserMapper userMapper,
                       PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Получает пользователя по имени.
     *
     * @param username имя пользователя
     * @return пользователь в виде DTO
     * @throws RuntimeException если пользователь не найден
     */
    public User getUser(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return userMapper.toDto(user);
    }

    /**
     * Обновляет данные пользователя.
     *
     * @param username имя пользователя
     * @param userDto новые данные пользователя
     * @return обновлённый пользователь
     * @throws RuntimeException если пользователь не найден
     */
    public User updateUser(String username, User userDto) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setPhone(userDto.getPhone());

        return userMapper.toDto(userRepository.save(user));
    }

    /**
     * Изменяет пароль пользователя.
     *
     * @param username имя пользователя
     * @param newPassword объект с текущим и новым паролем
     * @throws RuntimeException если текущий пароль неверный или пользователь не найден
     */
    public void changePassword(String username, NewPassword newPassword) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));


        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new RuntimeException("Wrong current password");
        }

        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));

        userRepository.save(user);
    }
    /**
     * Обновляет изображение пользователя.
     *
     * @param username имя пользователя
     * @throws RuntimeException если пользователь не найден
     */
    public void updateUserImage(String username) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
        userRepository.save(user);
    }
}
