package com.skypro.service.impl;

import com.skypro.dto.RegisterReq;
import com.skypro.entity.UserEntity;
import com.skypro.mapper.UserMapper;
import com.skypro.repository.UserRepository;
import com.skypro.service.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Реализация сервиса {@link AuthService} для аутентификации и регистрации.
 * <p>
 * Содержит бизнес-логику проверки учётных данных и создания новых пользователей.
 * </p>
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserMapper userMapper) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * Проверяет правильность введённых логина и пароля.
     *
     * @param userName логин пользователя
     * @param password пароль в открытом виде
     * @return {@code true}, если логин и пароль совпадают, иначе {@code false}
     */
    @Override
    public boolean login(String userName, String password) {
        return userRepository.findByUsername(userName)
                .map(user -> encoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    /**
     * Регистрирует нового пользователя в системе.
     * <p>
     * Проверяет, что логин ещё не занят. Если логин свободен,
     * создаёт сущность, хеширует пароль и сохраняет в БД.
     * </p>
     *
     * @param registerReq DTO с данными для регистрации
     * @return {@code true}, если регистрация прошла успешно,
     *         {@code false}, если пользователь с таким логином уже существует
     */
    @Override
    public boolean register(RegisterReq registerReq) {
        if (userRepository.findByUsername(registerReq.getUsername()).isPresent()) {
            return false;
        }
        UserEntity userEntity = userMapper.toEntity(registerReq);
        userEntity.setPassword(encoder.encode(registerReq.getPassword()));
        userRepository.save(userEntity);
        return true;
    }
}
