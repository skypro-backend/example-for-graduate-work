package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.exception.UsernameIsNotFoundException;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

/**
 * Service for user registration and authentication
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    /**
     * Checks if username is in a database and if entered password is equal to a password in a database
     * @param userName is a username, or an email
     * @param password is an entered password
     * @return false if password is wrong or true otherwise
     */
    @Override
    public boolean login(String userName, String password) {
        UserEntity userEntity = userRepository.findByEmail(userName).orElseThrow(()->new UsernameIsNotFoundException("Username is not found"));
        return encoder.matches(password, userEntity.getPassword());
    }

    /**
     * Registers a user
     * @param register is Register DTO consisting of username, password, firstName, lastName, phone and role
     * @return false if a user is already registered or true if a user was registered just now
     */
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