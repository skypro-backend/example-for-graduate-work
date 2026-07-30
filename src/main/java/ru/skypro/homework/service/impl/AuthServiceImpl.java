package ru.skypro.homework.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

/**
 * Реализация регистрации и авторизации пользователей.
 *
 * <p>Пользователи сохраняются в PostgreSQL,
 * а пароли хранятся в виде BCrypt-хеша.</p>
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            UserRepository userRepository,
            UserMapper userMapper,
            PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Регистрирует нового пользователя.
     *
     * @param register регистрационные данные
     * @return false, если username уже занят
     */
    @Override
    @Transactional
    public boolean register(Register register) {
        if (userRepository.existsByUsernameIgnoreCase(
                register.getUsername()
        )) {
            return false;
        }

        UserEntity user = userMapper.toEntity(register);

        user.setUsername(
                register.getUsername().trim()
        );

        user.setPassword(
                passwordEncoder.encode(
                        register.getPassword()
                )
        );

        userRepository.save(user);

        return true;
    }

    /**
     * Проверяет введённый пароль по BCrypt-хешу из базы.
     *
     * @param login данные для входа
     * @return true при успешной проверке
     */
    @Override
    @Transactional(readOnly = true)
    public boolean login(Login login) {
        return userRepository
                .findByUsernameIgnoreCase(login.getUsername())
                .map(user ->
                        passwordEncoder.matches(
                                login.getPassword(),
                                user.getPassword()
                        )
                )
                .orElse(false);
    }
}
