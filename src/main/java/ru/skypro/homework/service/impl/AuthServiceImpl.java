package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.repository.UserRepository;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    private final UserService userService;

    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserRepository userRepository, UserService userService, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.userService = userService;
        this.encoder = encoder;
    }
    /**
     * Авторизация
     */
    @Override
    public boolean login(String userName, String password) {
        if (!userService.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = userService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }
    /**
     * Регистрация
     */
    @Override
    public boolean register(RegisterReq registerReq, Role role) {
        if (userService.userExists(registerReq.getUsername())) {
            return false;
        }
        ru.skypro.homework.model.User newUser = new User();
        newUser.setUserName(registerReq.getUsername());
        newUser.setMail(registerReq.getUsername());
        newUser.setPassword(encoder.encode(registerReq.getPassword()));
        newUser.setFirstName(registerReq.getFirstName());
        newUser.setLastName(registerReq.getLastName());
        newUser.setPhone(registerReq.getPhone());
        newUser.setRole(role);
        userRepository.save(newUser);
        return true;
    }

}



