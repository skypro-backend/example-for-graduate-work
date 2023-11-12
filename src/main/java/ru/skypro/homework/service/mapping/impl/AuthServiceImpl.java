package ru.skypro.homework.service.mapping.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.skypro.homework.dto.authentication.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.mapping.UserMapper;

public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public AuthServiceImpl(PasswordEncoder encoder, UserDetailsService userDetailsService, UserRepository userRepository, UserMapper userMapper) {
        this.encoder = encoder;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }


    @Override
    public boolean login(String userName, String password) {
        if (userRepository.findByUsername(userName) == null) {
            return false;
        } else {
            UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
            return encoder.matches(password, userDetails.getPassword());
        }
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.findByUsername(register.getUsername()) == null) {
            UserEntity convertedFromDto = userMapper.registerDTOtoUserEntity(register);
            convertedFromDto.setPassword(encoder.encode(register.getPassword()));
            userRepository.save(convertedFromDto);
            return true;
        }
        return false;
    }
}
