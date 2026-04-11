package ru.skypro.homework.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public boolean login(String email, String password) {
        try {
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return passwordEncoder.matches(password, user.getPassword());
        } catch (UsernameNotFoundException e) {
            log.warn("Login failed for user: {}", email);
            return false;
        }
    }

    @Transactional
    public boolean register(Register register) {
        log.info("Регистрация нового пользователя: {}", register.getUsername());

        if (userRepository.existsByEmail(register.getUsername())) {
            log.warn("User already exists: {}", register.getUsername());
            return false;
        }

        UserEntity user = userMapper.toEntity(register);
        user.setPassword(passwordEncoder.encode(register.getPassword()));

        Role role = register.getRole();
        if (role == null) {
            user.setRole(user.setRole(Role.USER));
        } else {
            user.setRole(user.setRole(Role.USER));
        }

        userRepository.save(user);
        log.info("Регистрация пользователя выполнена: {}", register.getUsername());
        return true;
    }

    @Transactional
    public boolean changePassword(String email, String currentPassword, String newPassword) {
        log.info("Смена пароля пользователя: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            log.warn("Current password is incorrect for user: {}", email);
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        log.info("Пароль изменён: {}", email);
        return true;
    }
}