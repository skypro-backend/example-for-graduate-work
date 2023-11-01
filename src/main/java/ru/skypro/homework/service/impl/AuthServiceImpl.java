package ru.skypro.homework.service.impl;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.security.UserDetailsServiceImpl;
/**
 * The class with methods to login and register users
 */
@Service
public class AuthServiceImpl implements AuthService {


    private final UserDetailsServiceImpl userService;
    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    public AuthServiceImpl(UserDetailsServiceImpl userService,
                           PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.encoder = passwordEncoder;
    }

    /**
     * The method to login users
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
     * The method to register users
     */
    @Override
    public boolean register(Register register) {
        if (userService.userExists(register.getUsername())) {
            return false;
        }
        String password = encoder.encode(register.getPassword());
        userService.createUser(register, password);
        return true;
    }

}
