package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.models.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository repository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder encoder;

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return checkPasswords(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (repository.existsByEmail(register.getUsername())) {
            return false;
        }
        repository.save(User.builder()
                .email(register.getUsername())
                .password(encoder.encode(register.getPassword()))
                .firstName(register.getFirstName())
                .lastName(register.getLastName())
                .phone(register.getPhone())
                .role(register.getRole())
                .build());
        return true;
    }

    @Override
    public boolean checkPasswords(String source, String target) {
        return encoder.matches(source, target);
    }

}
