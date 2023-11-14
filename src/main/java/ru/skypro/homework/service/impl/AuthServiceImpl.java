package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.UserInfo;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserDetailsService manager;
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    public AuthServiceImpl(UserDetailsService manager,
                           UserRepository userRepository,
                           PasswordEncoder passwordEncoder) {
        this.manager = manager;
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @Override
    public boolean login(String userName, String password) {
        if (userRepository.findByEmail(userName).isEmpty()) {
            return false;
        }
        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register) {
        if (userRepository.findByEmail(register.getUsername()).isPresent()) {
            return false;
        }
        UserInfo userInfo  = new UserInfo();
        userInfo.setEmail(register.getUsername());
        userInfo.setPassword(encoder.encode(register.getPassword()));
        userInfo.setRole(register.getRole());
        userInfo.setPhone(register.getPhone());
        userInfo.setFirstName(register.getFirstName());
        userInfo.setLastName(register.getLastName());
        userRepository.save(userInfo);
        /*manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());*/
        return true;
    }

}
