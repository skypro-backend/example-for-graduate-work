package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.CustomUserDetailsService;


@Service
@RequiredArgsConstructor

public class AuthServiceImpl implements AuthService {


    private final UserRepository userRepository;
    private final CustomUserDetailsService customUserDetailsService;
    private final PasswordEncoder passwordEncoder;

    /**
     * login(String userName, String password) is a public method used for user authorization
     * @author SergeiAnishchenko + 1
     */
    @Override
    public boolean login(String userName, String password) {

        if (userRepository.findByUsername(userName).isEmpty()) {
            return false;
        }
        UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);
        return passwordEncoder
                .matches(password, userDetails.getPassword());
    }


    /**
     * register(RegisterDto registerDto) is a public method used for user registration
     * @author SergeiAnishchenko + 1
     */
    @Override
    public boolean register(RegisterDto registerDto) {
        if (userRepository.findByUsername(registerDto.getUsername()).isPresent()) {
            return false;
        }
        User user = new User();

        user.setUsername(registerDto.getUsername());
        user.setPassword(passwordEncoder
                .encode(registerDto.getPassword()));
        user.setFirstName(registerDto.getFirstName());
        user.setLastName(registerDto.getLastName());
        user.setPhone(registerDto.getPhone());
        user.setRole(registerDto.getRole());
        userRepository.save(user);
        return true;
    }

}