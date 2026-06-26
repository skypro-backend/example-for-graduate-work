package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void setPassword(NewPassword newPassword, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Проверка старого пароля через PasswordEncoder.matches()
        if (!passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            throw new org.springframework.security.authentication.BadCredentialsException("Wrong current password");
        }

        // Зашифровал новый пароль через encode() перед сохранением
        user.setPassword(passwordEncoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
        log.info("Password updated for user: {}", username);
    }

    @Override
    public UserDto getUser(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        return userMapper.toDto(user);
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        userMapper.updateUserFromDto(updateUser, user);
        userRepository.save(user);
        log.info("User info updated for: {}", username);
        return updateUser;
    }

    @Override
    public void updateUserImage(MultipartFile image, String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        try {
            user.setImageData(image.getBytes());
            user.setImage("/images/user/" + user.getId()); // Ссылка на эндпоинт ImageController
            userRepository.save(user);
            log.info("Avatar successfully uploaded for user: {}", username);
        } catch (java.io.IOException e) {
            log.error("Failed to upload avatar", e);
            throw new RuntimeException("Image upload failed");
        }
    }
}