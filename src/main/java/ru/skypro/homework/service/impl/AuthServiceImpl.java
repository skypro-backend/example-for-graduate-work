package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.security.SecurityUserDetailsService;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    //private final UserDetailsManager manager;

    private final SecurityUserDetailsService securityUserDetailsService;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(SecurityUserDetailsService securityUserDetailsService,
                           PasswordEncoder passwordEncoder) {
        this.securityUserDetailsService = securityUserDetailsService;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!securityUserDetailsService.userExists(userName)) {
            return false;
        }
        UserDetails userDetails = securityUserDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (securityUserDetailsService.userExists(register.getUsername())) {
            return false;
        }
        securityUserDetailsService.createUser(register);
        return true;
    }

}
