package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.mapper.AdMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import java.nio.CharBuffer;
import java.time.LocalDateTime;
@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {

    private final UserService userService;

    private final PasswordEncoder encoder;

    private final UserMapper userMapping;

    @Override
    public boolean login(String userName, String password) {
        if (!userService.userExists(userName)) {
            return false;
        }
        return encoder.matches(CharBuffer.wrap(password),
                userService.loadUserByUsername(userName).getPassword());
    }

    @Override
    public boolean register(RegisterDTO register) {
        if (userService.userExists(register.getUsername())) {
            return false;
        }


        userService.post(userMapping.mapToUser(register));


//                User.builder()
//                        .password(encoder.encode(CharBuffer.wrap(register.getPassword())))
//                        .username(register.getUsername())
//                        .firstName(register.getFirstName())
//                        .lastName(register.getLastName())
//                        .phone(register.getPhone())
//                        .role(register.getRole())
//                        .isEnabled(true)
//                        .nonLocked(true)
//                        .nonExpired(true)
//                        .nonCredentialsExpired(true)
//                        .registrationDate(LocalDateTime.now())
//                        .build());

        return true;
    }
}
