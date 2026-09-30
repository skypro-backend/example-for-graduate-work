package ru.skypro.homework.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            userName,
                            password
                    )
            );
            return true;
        } catch (AuthenticationException e) {
            return false;
        }
    }

    @Override
    public boolean register(Register register) {

        if (userRepository.existsByUsername(register.getUsername())) {
            return false;
        }

        UserEntity user = new UserEntity();

        user.setUsername(register.getUsername());
        user.setPassword(
                passwordEncoder.encode(register.getPassword())
        );
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());

        if (register.getRole() == null) {
            user.setRole(Role.USER);
        } else {
            user.setRole(register.getRole());
        }

        userRepository.save(user);

        return true;
    }
}