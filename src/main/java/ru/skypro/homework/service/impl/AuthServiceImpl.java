package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.Role;
import ru.skypro.homework.entity.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

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

        User user = new User();

        user.setEmail(register.getUsername());

        user.setPassword(
                passwordEncoder.encode(
                        register.getPassword()
                )
        );

        user.setFirstName(register.getFirstName());

        user.setLastName(register.getLastName());

        user.setPhone(register.getPhone());

        user.setRole(
                register.getRole() != null
                        ? Role.valueOf(register.getRole().name())
                        : Role.USER
        );


        userRepository.save(user);

        return true;
    }
}