package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.AuthService;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService manager;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;

    @Override
    public boolean login(String username, String password) {
        if (!((UserDetailsManagerImpl) manager).userExists(username)) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(username);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register, Role role) {
        if (((UserDetailsManagerImpl) manager).userExists(register.getUsername())) {
            return false;
        }
        register.setRole(role);
        register.setPassword(encoder.encode(register.getPassword()));
        ((UserDetailsManagerImpl) manager).createUser(mapper.registerReqDtoToEntity(register));
        return true;
    }
    @Override
    public boolean setPassword(NewPasswordDto newPassword, String name) {
        if (encoder.matches(newPassword.getCurrentPassword(), manager.loadUserByUsername(name).getPassword())) {
            ((UserDetailsManagerImpl) manager).changePassword(encoder.encode(newPassword.getNewPassword()), name);
            return true;
        }
        return false;
    }
}
