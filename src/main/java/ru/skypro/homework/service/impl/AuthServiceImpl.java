package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.JavaKidsUserDetailsManager;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    private final /*UserDetailsManager*/
            JavaKidsUserDetailsManager manager;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(JavaKidsUserDetailsManager manager,
                           PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        logger.info("User name: " + userName + " | Password: " + password);
        if ("Java@Kids.Team".equals(userName)) {
            return true;
        } //todo: delete upon completion of development
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        logger.info("password: " + userDetails.getPassword()
                + " | matches: " + encoder.matches(password, userDetails.getPassword()));
        String encodedPassword = encoder.encode(password);
        logger.info("Provided password: " + password);
        logger.info("Encoded password: " + encodedPassword);
        logger.info("Password in database: " + userDetails.getPassword());
        boolean matches = encoder.matches(password, userDetails.getPassword());
        logger.info("Password matches: " + matches);
        /* TODO: remove useless logger statements */
        return matches;
    }

    @Override
    public boolean register(RegisterDto registerDto) {
        if (manager.userExists(registerDto.getUsername())) {
            return false;
        }

        String rawPassword = registerDto.getPassword();
        registerDto.setPassword(encoder.encode(rawPassword));

        manager.createUser(registerDto);

        return true;
    }

}
