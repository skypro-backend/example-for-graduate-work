package ru.skypro.homework.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Transactional(readOnly = true)
    public UserDto getCurrentUser(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        return userMapper.toDto(user);
    }

    @Transactional
    public UserDto updateCurrentUser(String email, UpdateUserDto updateUserDto) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        userMapper.updateEntityFromDto(updateUserDto, user);
        return userMapper.toDto(userRepository.save(user));
    }

    @Transactional
    public void updatePassword(String email, String newPassword) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);
    }

    @Transactional
    public void updateAvatar(String email, String image) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Пользователь не найден"));
        user.setImage(image);
        userRepository.save(user);
    }
}
