package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

/**
 * Реализация сервиса аутентификации и регистрации.
 * Обрабатывает логику входа и создания новых пользователей.
 * Основные функции:
 * - проверка логина и пароля пользователя;
 * - регистрация нового пользователя с хешированием пароля.
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    /**
     * Авторизация пользователя.
     * Проверяет существование пользователя по email и совпадение пароля.
     * @param userName логин пользователя (email)
     * @param password введённый пароль
     * @return true если пароль совпадает, иначе false
     */
    @Override
    public boolean login(String userName, String password) {
        return userRepository.findByEmail(userName)
                .map(user -> encoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    /**
     * Регистрация нового пользователя.
     * Проверяет, что email не занят, создаёт сущность пользователя с хешированным паролем и сохраняет в БД.
     * @param register DTO с данными для регистрации
     * @return true если регистрация успешна, иначе false
     */
    @Override
    public boolean register(Register register) {
        if (userRepository.findByEmail(register.getUsername()).isPresent()) {
            return false;
        }

        UserEntity user = new UserEntity();
        user.setEmail(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setRole(register.getRole().name());

        userRepository.save(user);
        return true;
    }
}
