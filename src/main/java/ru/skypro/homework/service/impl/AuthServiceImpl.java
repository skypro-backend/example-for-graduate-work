package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.userDetailsService.BackEndUserDetailsService;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.logging.Logger;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private Logger LoggerFactory;
    private final BackEndUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final Logger logger = (Logger) LoggerFactory.getLogger(String.valueOf(AuthServiceImpl.class));

    @Override
    public boolean login(Login login) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(login.getUsername());
        if (userDetails == null) {
            return false;
        }
        logger.info(String.valueOf(passwordEncoder.matches(login.getPassword(), userDetails.getPassword())));
        return passwordEncoder.matches(login.getPassword(), userDetails.getPassword());
    }

    @Override
    public boolean register(Register registerDto) {
        if (userDetailsService.loadUserByUsername(registerDto.getUsername()) != null) {
            return false;
        }
        User user = userMapper.mapFromRegister(registerDto);
        userRepository.save(user);
        return true;
    }

}
