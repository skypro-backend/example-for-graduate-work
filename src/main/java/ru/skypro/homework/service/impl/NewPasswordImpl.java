package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.NewPasswordService;

import java.security.Principal;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class NewPasswordImpl implements NewPasswordService {
    private final Logger logger = LoggerFactory.getLogger(NewPasswordImpl.class);

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    @Override
    public void setPassword(String currentPassword,  String newPassword) {
        logger.info("Current password: " + currentPassword +
                "| New password: " + newPassword);

    }

    public void setPassword(String currentPassword, String newPassword, Principal principal) {
        if (principal == null) {
            return;
        }
        logger.info("Current password: " + currentPassword +
                "| New password: " + newPassword);
        Optional<User> userOptional = userRepository.findByEmail(principal.getName());
        if (userOptional.isEmpty()) {
            return;
        }
        User user = userOptional.get();
        if (currentPassword.isEmpty() | newPassword.isEmpty()) {
            return;
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user = userRepository.save(user);
        logger.info("User: " + user.toString() + " set new password: " + newPassword
                + " encoded: " + user.getPassword());
    }
}
