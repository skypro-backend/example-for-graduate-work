package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReqDTO;
import ru.skypro.homework.security.CustomUserDetailsService;

@Service
public class AuthService {

    private final CustomUserDetailsService manager;
    private final PasswordEncoder encoder;

    public AuthService(CustomUserDetailsService manager) {
        this.manager = manager;
        this.encoder = new BCryptPasswordEncoder();
    }


    public boolean login(String userName, String password) {
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    public boolean register(RegisterReqDTO dto) {
        manager.createUser(dto);
        return true;
    }

}
