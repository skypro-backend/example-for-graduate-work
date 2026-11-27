package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.security.CustomUserDetails;
import ru.skypro.homework.security.CustomUserDetailsManager;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final CustomUserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    @Transactional
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        UserEntity user = userMapper.toEntity(register);
        user.setPassword(encoder.encode(register.getPassword()));
        user.setRole(Optional.ofNullable(register.getRole()).orElse(Role.USER));
        manager.createUser(new CustomUserDetails(user));
        return true;
    }
}
