package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.exception.UserNotRegisteredException;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

/**
 * Класс-сервис, реализующий интерфейс {@link AuthService}
 */
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService manager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    public AuthServiceImpl(UserDetailsService manager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    /**
     * Метод для аутентификации пользователя
     * @param userName
     * @param password
     * @return true, если пользователь прошел аутентификацию
     */
    @Override
    @Transactional
    public boolean login(String userName, String password) {
        if (userRepository.findByEmail(userName).isEmpty()) {
            logger.info("Пользователь не зарегистрирован");
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    /**
     * Метод для регистрации пользователя
     * @param register
     * @return true, если пользователь прошел регистрацию
     */
    @Override
    public boolean register(Register register) {
        if (userRepository.findByEmail(register.getUsername()).isPresent()) {
            logger.info("Пользователь уже зарегистрирован");
            return false;
        }
        UserInfo userInfo  = new UserInfo();
        userInfo.setEmail(register.getUsername());
        userInfo.setPassword(encoder.encode(register.getPassword()));
        userInfo.setRole(register.getRole());
        userInfo.setPhone(register.getPhone());
        userInfo.setFirstName(register.getFirstName());
        userInfo.setLastName(register.getLastName());
        userRepository.save(userInfo);

        return true;
    }

    /**
     * Метод, возвращающий текущего авторизованного пользователя
     * @return {@link UserInfo}
     */
    @Transactional
    public UserInfo getCurrentUser() {
        Authentication authenticationUser = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByEmail(authenticationUser.getName()).orElseThrow(UserNotRegisteredException::new);
    }
}
