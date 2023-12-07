package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;

    private final UserRepository userRepository;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.encoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String userName, String password) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(userName);
        if (userEntityOptional.isEmpty()) {
            return false;
        }
        String encryptedPassword = new BCryptPasswordEncoder().encode(password);
        return encoder.matches(password,userEntityOptional.get().getPassword());
    }

    @Override
    public boolean register(Register register) {
        Optional<UserEntity> userEntityOptional = userRepository.findByUsername(register.getUsername());
        if (userEntityOptional.isPresent()) {
            return false;
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(register.getPassword());
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(register.getUsername());
        userEntity.setFirstName(register.getFirstName());
        userEntity.setLastName(register.getLastName());
        userEntity.setPhone(register.getPhone());
        userEntity.setPassword(encryptedPassword);
        userEntity.setRole(register.getRole());

        userRepository.save(userEntity);

        return true;
    }

}