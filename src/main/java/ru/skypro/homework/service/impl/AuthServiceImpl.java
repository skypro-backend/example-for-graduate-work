package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import java.util.Optional;

/**
 * Реализация сервиса аутентификации и регистрации {@link ru.skypro.homework.service.AuthService}.
 * Интегрирует проверку паролей через BCrypt и регистрирует новых пользователей
 * с автоматическим хешированием секретных данных перед записью в базу данных PostgreSQL.
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    /**
     * {@inheritDoc}
     * <p>
     * Выполняет поиск пользователя по email и сравнивает хэш пароля.
     */
    @Override
    public boolean login(String userName, String password) {
        Optional<User> userOpt = userRepository.findByEmail(userName);
        if (userOpt.isEmpty()) {
            return false;
        }
        return encoder.matches(password, userOpt.get().getPassword());
    }

    /**
     * {@inheritDoc}
     * <p>
     * Проверяет уникальность логина, хэширует пароль и сохраняет новую сущность.
     */
    @Override
    public boolean register(Register register) {
        if (userRepository.findByEmail(register.getUsername()).isPresent()) {
            return false;
        }
        User user = new User();
        user.setEmail(register.getUsername());
        user.setPassword(encoder.encode(register.getPassword()));
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        user.setRole(register.getRole());
        userRepository.save(user);
        return true;
    }
}