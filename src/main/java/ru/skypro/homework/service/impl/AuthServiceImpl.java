package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public boolean login(String username, String password) {
        return userRepository.findByEmail(username)
                .map(user -> passwordEncoder.matches(password, user.getPassword()))
                .orElse(false);
    }

    @Override
    public Optional<Integer> register(Register register) {
        if (userRepository.existsByEmail(register.getUsername())) {
            return Optional.empty();
        }
        User user = userMapper.toEntity(register);
        user.setPassword(passwordEncoder.encode(register.getPassword()));
        User savedUser = userRepository.save(user);
        return Optional.of(savedUser.getId());
    }
}
