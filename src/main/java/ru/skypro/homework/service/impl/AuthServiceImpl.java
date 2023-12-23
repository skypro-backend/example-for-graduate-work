package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.UserDetailsServiceImpl;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final PasswordEncoder encoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final UserEntityRepository userEntityRepository;

    @Override
    public boolean login(String userName, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }
    @Override
    public boolean register(Register register) {

        UserEntity userEntity = userEntityRepository.findByUsername(register.getUsername()).get();
        if (userEntity != null) {
            logger.warn("User with current username (email) had been registered before!");
            return false;
        }
               userEntity =  UserEntity.builder()
                        .password(encoder.encode(register.getPassword()))
                        .username(register.getUsername())
                        .firstName(register.getFirstName())
                        .lastName(register.getLastName())
                        .phone(register.getPhone())
                        .role(register.getRole())
                        .build();

        userEntityRepository.save(userEntity);
        logger.info("User with username (email) " + register.getUsername() + " has been registered!" );
        return true;
    }

}
