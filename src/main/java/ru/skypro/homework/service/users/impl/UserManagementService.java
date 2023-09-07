package ru.skypro.homework.service.users.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.auth.LoginDto;
import ru.skypro.homework.dto.auth.RegisterDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.entity.users.UserCustom;
import ru.skypro.homework.mappers.UserMapper;

@Service
public class UserManagementService {

    private final UserCustomService userCustomService;
    private final UserMapper userMapper;
    private final PasswordEncoder encoder;

    public UserManagementService(UserCustomService userCustomService, UserMapper userMapper, PasswordEncoder encoder) {
        this.userCustomService = userCustomService;
        this.userMapper = userMapper;
        this.encoder = encoder;
    }

    public boolean register(RegisterDto registerDto) {
        User user = userMapper.toEntity(registerDto);
        UserCustom userCustom = new UserCustom(user);
        if (!userCustomService.userExists(userCustom.getUsername())) {
            userCustomService.createUser(userCustom);
            return true;
        } else {
            return false;
        }
    }

    public boolean login(LoginDto loginDto) {
        String username = loginDto.getUsername();
        if (userCustomService.userExists(username)) {
            String currentPassword = loginDto.getPassword();
            UserDetails userDetails = userCustomService.loadUserByUsername(username);
            return encoder.matches(currentPassword, userDetails.getPassword());
        } else {
            return false;
        }
    }
}