package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.*;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService manager;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final UserMapper userMapper;

    @Override
    public boolean login(String userName, String password) {
        if (!userService.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (userService.userExists(register.getUsername())) {
            return false;
        }
        register.setPassword(encoder.encode(register.getPassword()));
        userService.createUser(userMapper.registerReqDtoToEntity(register));
        return true;
    }

    @Override
    public boolean setPassword(NewPasswordDto newPassword, String name) {
        if (encoder.matches(newPassword.getCurrentPassword(), manager.loadUserByUsername(name).getPassword())) {
            userService.changePassword(encoder.encode(newPassword.getNewPassword()), name);
            return true;
        }
        return false;
    }

}
