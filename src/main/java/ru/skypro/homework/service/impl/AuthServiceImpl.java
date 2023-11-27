package ru.skypro.homework.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.PrimerService;

import java.nio.CharBuffer;

@Service
public class AuthServiceImpl implements AuthService {

 //   private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final PrimerService primerService;
    private final UserMapper userMapper;

    public AuthServiceImpl(PasswordEncoder passwordEncoder, PrimerService primerService, UserMapper userMapper) {
//        this.manager = manager;
        this.encoder = passwordEncoder;
        this.primerService = primerService;
        this.userMapper = userMapper;
    }

    @Override
    public boolean login(String userName, String password) {
        if (!primerService.userExists(userName)) {
            return false;
        }

        UserDetails userDetails = primerService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(RegisterDTO register) {
        if (primerService.userExists(register.getUsername())) {
            return false;
        }
        primerService.createUser(userMapper.mapToUser(register));
/*                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());*/
        return true;
    }
    public boolean setPassword (String username, NewPasswordDTO newPassword) {
        if (login(username, newPassword.getCurrentPassword())) {
            User user = primerService.findUser(username);
            user.setPassword(encoder.encode(newPassword.getNewPassword()));
            primerService.updatePassword(user);
            return true;
        } return false;
    }
}