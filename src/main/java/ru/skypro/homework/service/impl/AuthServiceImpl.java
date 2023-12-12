package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    @Override
    public boolean login(String userName, String password) {
        UserEntity userEntity = userRepository.findByEmail(userName).orElseThrow();
        return encoder.matches(password, userEntity.getPassword());
    }

    @Override
    public boolean register(Register register) {
        Optional<UserEntity> userEntityOptional = userRepository.findByEmail(register.getUsername());
        if (userEntityOptional.isPresent()) {
            return false;
        }

        UserEntity userEntity = UserEntity.builder().
                email(register.getUsername()).
                firstName(register.getFirstName()).
                lastName(register.getLastName()).
                phone(register.getPhone()).
                role(register.getRole()).
                imageEntity(null).
                password(new BCryptPasswordEncoder().encode(register.getPassword())).build();
        userRepository.save(userEntity);
        return true;
    }

}