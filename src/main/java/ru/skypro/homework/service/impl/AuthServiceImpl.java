package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

/**
 * Реализация сервиса аутентификации и регистрации {@link AuthService}.
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            log.warn("Пользователь с именем {} не найден", userName);
            return false;
        }

        UserDetails userDetails = manager.loadUserByUsername(userName);

        if (!encoder.matches(password, userDetails.getPassword())) {
            log.warn("Неверный пароль для пользователя {}", userName);
            return false;
        }

        log.info("Пользователь {} успешно авторизован", userName);
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @Transactional
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            log.warn("Пользователь {} уже существует", register.getUsername());
            return false;
        }

        UserEntity newUser = new UserEntity();
        newUser.setEmail(register.getUsername());
        newUser.setPassword(encoder.encode(register.getPassword()));
        newUser.setFirstName(register.getFirstName());
        newUser.setLastName(register.getLastName());
        newUser.setPhone(register.getPhone());
        newUser.setRole(register.getRole());

        userRepository.save(newUser);

        log.info("Пользователь {} успешно зарегистрирован", register.getUsername());
        return true;
    }
}