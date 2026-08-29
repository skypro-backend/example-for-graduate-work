package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final DataSource dataSource;

    @Override
    @SneakyThrows
    public boolean login(String userName, String password) {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT password FROM users_security WHERE username = ?")) {
            ps.setString(1, userName);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return encoder.matches(password, rs.getString("password"));
                }
            }
        }
        return false;
    }

    @Override
    @SneakyThrows
    public boolean register(Register register) {
        // Проверяем, есть ли уже такой логин
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT username FROM users_security WHERE username = ?")) {
            ps.setString(1, register.getUsername());
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return false;
                }
            }
        }

        // 1. Сохраняем данные для входа в users_security
        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement("INSERT INTO users_security (username, password, enabled) VALUES (?, ?, true)")) {
            ps.setString(1, register.getUsername());
            ps.setString(2, encoder.encode(register.getPassword()));
            ps.executeUpdate();
        }

        // 2. Сохраняем профиль пользователя в users
        UserEntity entity = userMapper.registerToEntity(register);
        entity.setEmail(register.getUsername());
        userRepository.save(entity);

        return true;
    }
}