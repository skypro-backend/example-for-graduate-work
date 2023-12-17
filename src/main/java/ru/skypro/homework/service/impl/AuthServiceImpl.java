package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.exception.AuthorizationException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;


    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = userService.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(password, encryptedPassword);
    }

    @Override
    public boolean register(RegisterDTO register, Role role) {
        if (userRepository.findUserByEmail(register.getUsername()).isPresent()) {
            return false;
        }
        User userRegestration = userMapper.mapRegisterDTOtoUser(register);
        userRegestration.setRole(role);
        userRegestration.setPassword(encoder.encode(userRegestration.getPassword()));
        userRepository.save(userRegestration);
        return true;
    }

    @Override
    public void updatePassword(NewPasswordDTO newPassword) {
        User user = userService.findAuthUser()
                .filter(u -> encoder.matches(newPassword.getCurrentPassword(), u.getPassword()))
                .orElseThrow(() -> new AuthorizationException("Invalid current password"));

        user.setPassword(encoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);
    }

}
