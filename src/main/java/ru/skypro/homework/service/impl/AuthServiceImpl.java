package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean login(String username, String password) {
        boolean success = userRepository.findByEmail(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
        if (!success) {
            log.warn("Неудачная попытка входа для пользователя {}", username);
        }
        return success;
    }

    @Override
    public Optional<Integer> register(Register register) {
        if (userRepository.existsByEmail(register.getUsername())) {
            log.warn("Попытка регистрации с уже существующим логином {}", register.getUsername());
            return Optional.empty();
        }
        User user = userMapper.toEntity(register);
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        User savedUser = userRepository.save(user);
        log.info("Зарегистрирован новый пользователь id={}, роль={}", savedUser.getId(), savedUser.getRole());
        return Optional.of(savedUser.getId());
    }
}