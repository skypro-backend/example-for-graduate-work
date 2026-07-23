package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean login(String userName, String password) {
        return userRepository.findByEmailIgnoreCase(userName)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.existsByEmailIgnoreCase(register.getUsername())) {
            log.warn("Попытка регистрации с существующим email: {}", register.getUsername());
            return false;
        }

        UserEntity user = UserEntity.builder()
                .email(register.getUsername())
                .password(passwordEncoder.encode(register.getPassword()))
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .phone(register.getPhone())
                .role(register.getRole() != null ? register.getRole().name() : "USER")
                .build();

        userRepository.save(user);
        log.info("Зарегистрирован новый пользователь: {}", user.getEmail());
        return true;
    }
}