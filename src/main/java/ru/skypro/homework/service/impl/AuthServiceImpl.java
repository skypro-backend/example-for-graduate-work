package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder encoder;
    private final UserMapper userMapper;


    @Override
    public boolean login(String userName, String password) {
        log.info("Attempting login for user: {}", userName);
        UserDetails userDetails = userService.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        boolean loginSuccess = encoder.matches(password, encryptedPassword);
        log.info("Login {} for user: {}", loginSuccess ? "successful" : "failed", userName);
        return loginSuccess;
    }

    @Override
    public boolean register(RegisterDTO register, Role role) {
        String username = register.getUsername();
        log.info("Attempting registration for user: {}", username);

        if (userRepository.findUserByEmail(username).isPresent()) {
            log.warn("Registration failed. User {} already exists.", username);
            return false;
        }

        User userRegistration = userMapper.mapRegisterDTOtoUser(register);
        userRegistration.setRole(role);
        userRegistration.setPassword(encoder.encode(userRegistration.getPassword()));
        userRepository.save(userRegistration);

        log.info("Registration successful for user: {}", username);
        return true;
    }

    @Override
    public void updatePassword(NewPasswordDTO newPassword) {
        log.info("Updating password for authenticated user");
        User user = userService.findAuthUser()
                .filter(u -> encoder.matches(newPassword.getCurrentPassword(), u.getPassword()))
                .orElseThrow(() -> {
                    log.error("Update password failed. Invalid current password.");
                    return new AuthorizationException("Invalid current password");
                });

        user.setPassword(encoder.encode(newPassword.getNewPassword()));
        userRepository.save(user);

        log.info("Password updated successfully for user: {}", user.getEmail());
    }

}
