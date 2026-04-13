package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional(readOnly = true)
    public boolean login(String email, String password) {
        try {
            UserEntity user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            return passwordEncoder.matches(password, user.getPassword());
        } catch (UsernameNotFoundException e) {
            log.warn("Login failed for user: {}", email);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean register(Register register) {
        log.info("Email: {}", register.getUsername());
        log.info("Password: [PROTECTED]");
        log.info("FirstName: {}", register.getFirstName());
        log.info("LastName: {}", register.getLastName());
        log.info("Phone: {}", register.getPhone());
        log.info("Role: {}", register.getRole());

        try {
            if (userRepository.existsByEmail(register.getUsername())) {
                log.warn("User already exists: {}", register.getUsername());
                return false;
            }

            UserEntity user = new UserEntity();
            user.setEmail(register.getUsername());
            user.setFirstName(register.getFirstName());
            user.setLastName(register.getLastName());
            user.setPhone(register.getPhone());

            String encodedPassword = passwordEncoder.encode(register.getPassword());
            user.setPassword(encodedPassword);

            if (register.getRole() == null) {
                user.setRole(Role.USER);
            } else {
                user.setRole(register.getRole());
            }

            UserEntity savedUser = userRepository.save(user);
            log.info("User saved successfully with ID: {}", savedUser.getId());

            return true;

        } catch (Exception e) {
            log.error("Registration error: ", e);
            return false;
        }
    }

    @Override
    @Transactional
    public boolean changePassword(String email, String currentPassword, String newPassword) {
        log.info("Changing password for user: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
            log.warn("Current password is incorrect for user: {}", email);
            return false;
        }

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
        log.info("Password changed successfully for user: {}", email);
        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        log.debug("Getting user by email: {}", email);

        return userRepository.findByEmail(email)
                .map(userMapper::toDto);
    }
}