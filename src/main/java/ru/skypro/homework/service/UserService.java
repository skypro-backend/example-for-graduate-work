package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public Optional<User> getUserByEmail(String email) {
        log.debug("Getting user by email: {}", email);

        return userRepository.findByEmail(email)
                .map(userMapper::toDto);
    }

    @Transactional
    public Optional<User> updateUser(String email, UpdateUser updateUser) {
        log.info("Updating user: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        if (updateUser.getFirstName() != null) {
            user.setFirstName(updateUser.getFirstName());
        }
        if (updateUser.getLastName() != null) {
            user.setLastName(updateUser.getLastName());
        }
        if (updateUser.getPhone() != null) {
            user.setPhone(updateUser.getPhone());
        }

        UserEntity updated = userRepository.save(user);
        return Optional.of(userMapper.toDto(updated));
    }

    @Transactional
    public void updateUserImage(String email, String imageUrl) {
        log.info("Updating avatar for user: {}", email);

        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found: " + email));

        user.setImage(imageUrl);
        userRepository.save(user);
    }

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
        return true;
    }
}