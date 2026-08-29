package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.ImageService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;
    private final ImageService imageService;
    private final DataSource dataSource;

    @Override
    @SneakyThrows
    public void setPassword(NewPassword newPassword, String username) {
        // Меняем пароль напрямую в нашей таблице безопасности через SQL
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("UPDATE users_security SET password = ? WHERE username = ?")) {
            ps.setString(1, encoder.encode(newPassword.getNewPassword()));
            ps.setString(2, username);
            ps.executeUpdate();
        }
    }

    @Override
    public User getUser(String username) {
        UserEntity entity = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        return userMapper.toDto(entity);
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser, String username) {
        UserEntity entity = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));
        userMapper.updateUserFromDto(updateUser, entity);
        userRepository.save(entity);
        return updateUser;
    }

    @Override
    @SneakyThrows
    public void updateUserImage(MultipartFile image, String username) {
        UserEntity entity = userRepository.findByEmail(username)
                .orElseThrow(() -> new IllegalArgumentException("Пользователь не найден"));

        String fileName = imageService.uploadImage(image);
        entity.setImage("/ads/image/" + fileName);
        userRepository.save(entity);
    }
}