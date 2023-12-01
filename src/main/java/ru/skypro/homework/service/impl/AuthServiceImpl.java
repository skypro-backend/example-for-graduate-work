package ru.skypro.homework.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.exception.IncorrectPasswordException;
import ru.skypro.homework.mapper.CommentMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Comment;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.utils.MethodLog;

@Slf4j
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final MyUserDetailsService myUserDetailsService;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, MyUserDetailsService myUserDetailsService, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.myUserDetailsService = myUserDetailsService;
        this.encoder = passwordEncoder;
    }

    /**
     * Логика аутентификации пользователя.
     * Метод использует {@link MyUserDetailsService#loadUserByUsername(String)}
     * {@link PasswordEncoder#matches(CharSequence, String)}
     * @param userName - логин пользователя
     * @param password - пароль пользователя
     * @return true, если аутентификация прошла успешно, иначе - false.
     */
    @Override
    public boolean login(String userName, String password) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());

        UserDetails userDetails = myUserDetailsService.loadUserByUsername(userName);
        if (!encoder.matches(password, userDetails.getPassword())) {
            throw new IncorrectPasswordException("Incorrect password");
        }
        return true;
    }

    /**
     * Логика регистрации нового пользователя.
     * Метод использует {@link UserRepository#findByEmail(String)}
     * {@link PasswordEncoder#encode(CharSequence)}
     * {@link UserMapper#registerDTOToUser(RegisterDTO)}
     * @param register - информация о пользователе, который необходимо зарегистрировать.
     *                 Модель класса {@link RegisterDTO}
     * @return true, если регистрация прошла успешно, иначе - false.
     */
    @Override
    public boolean register(RegisterDTO register) {
        log.info("Использован метод сервиса: {}", MethodLog.getMethodName());
        if (userRepository.findByEmail(register.getUsername()) != null) {
            return false;
        }
        register.setPassword(encoder.encode(register.getPassword()));
        userRepository.save(UserMapper.INSTANCE.registerDTOToUser(register));
        return true;
    }

}
