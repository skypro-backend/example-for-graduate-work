package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    public boolean login(String username, String password) {

        return userRepository.findByEmail(username)
                .map(user ->
                        passwordEncoder.matches(
                                password,
                                user.getPassword()
                        )
                )
                .orElse(false);
    }

    @Override
    public boolean register(Register register) {

        if (userRepository.findByEmail(register.getUsername())
                .isPresent()) {

            return false;
        }

        User user =
                userMapper.registerToEntity(register);

        user.setPassword(
                passwordEncoder.encode(
                        register.getPassword()
                )
        );

        user.setRole(Role.USER);

        userRepository.save(user);

        return true;
    }
}