package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.repo.UserRepo;
import ru.skypro.homework.service.AuthService;


@Service
public class AuthServiceImpl implements AuthService {

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserRepo userRepo;


    public AuthServiceImpl(UserDetailsManager manager,
                           PasswordEncoder passwordEncoder,
                           UserRepo userRepo) {
        this.manager = manager;
        this.encoder = passwordEncoder;
        this.userRepo = userRepo;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!manager.userExists(userName)) {
            logger.info("AuthService login function if user not exists");
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        logger.info("Password in login: {}, Password in DB: {}", password,userDetails.getPassword());
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (manager.userExists(register.getUsername())) {
            return false;
        }
        logger.info("Register password :{}", register.getPassword());
        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());
        UserEntity user = userRepo.findByLogin(register.getUsername());
        if (user == null){
            return false;
        }
        logger.info("After Register in manager password: {}", user.getPassword());
        user.setRole(register.getRole());
        user.setFirstName(register.getFirstName());
        user.setLastName(register.getLastName());
        user.setPhone(register.getPhone());
        userRepo.save(user);
        return true;
    }
}
