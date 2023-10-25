package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Override
    public boolean login(final String username, final String password) {
        try {
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);
            return encoder.matches(password, userDetails.getPassword());
        } catch (UsernameNotFoundException e) {
            return false;
        }
    }

    @Override
    public boolean register(final RegisterDto registerDto) {
        if (userRepository.existsByLogin(registerDto.username())) {
            return false;
        }

        User user = userMapper.toEntity(registerDto);
        userRepository.save(user);
        return true;
    }
}