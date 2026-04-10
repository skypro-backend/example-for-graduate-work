package ru.skypro.homework.service;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

/**
 * Сервис для работы с аутентификацией и регистрацией пользователей.
 * Отвечает за создание пользователей и проверку их учетных данных.
 */
@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }


    /**
     * Регистрирует нового пользователя.
     *
     * @param request данные для регистрации (username и password)
     * @throws RuntimeException если пользователь уже существует
     */
    public void register(Register request) {
        if (userRepository.findByUsername(request.getUsername()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        UserEntity user = new UserEntity();
        user.setUsername(request.getUsername());
        user.setPassword(encoder.encode(request.getPassword()));


        user.setRole(Role.USER);
        userRepository.save(user);
    }


    /**
     * Проверяет учетные данные пользователя.
     *
     * @param username имя пользователя
     * @param password пароль пользователя
     * @throws BadCredentialsException если имя пользователя или пароль неверны
     */
    public void login(String username, String password) {
        UserEntity user = userRepository.findByUsername(username)
                .orElseThrow(() -> new BadCredentialsException("Invalid username or password"));

        if (!encoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Invalid username or password");
        }

    }
}