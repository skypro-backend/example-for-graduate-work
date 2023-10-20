package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.config.SecurityUserDetailsService;
import ru.skypro.homework.exeptions.BadRequestException;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.entities.UserEntity;
import ru.skypro.homework.service.repositories.UserRepository;

@Service
public class AuthServiceImpl implements AuthService {

    private final SecurityUserDetailsService securityUserDetailsService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthServiceImpl(SecurityUserDetailsService securityUserDetailsService, UserRepository userRepository, UserMapper userMapper) {
        this.securityUserDetailsService = securityUserDetailsService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(userName);
        return passwordEncoder().matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.findByEmail(register.getUsername()) != null) {
            throw new BadRequestException(String.format("Пользователь с ником \"%s\" cуществует",
                    register.getUsername())
            );
        }

        var userEntity = userMapper.toUserEntity(register);
        userRepository.saveAndFlush(userEntity);
        return true;
    }

}
