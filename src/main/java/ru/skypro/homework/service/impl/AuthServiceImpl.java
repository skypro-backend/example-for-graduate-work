package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.request.RegisterRequestDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.BadRequestException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

/**
 * Сервис аутентификации и регистрации пользователей.
 * Отвечает за:
 * - логин пользователя,
 * - регистрацию нового пользователя.
 */
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    /**
     * Выполняет аутентификацию пользователя.
     * @param username логин
     * @param password пароль
     */
    @Override
    public void login(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    /**
     * Регистрация нового пользователя.
     * @throws BadRequestException если пользователь уже существует
     */
    @Override
    public void register(RegisterRequestDto register) {
        if (userRepository.existsByUsername(register.getUsername())) {
            throw new BadRequestException("User already exists");
        }
        User user = new User();
        user.setUsername(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setRole(register.getRole());
        userRepository.save(user);
    }
}
