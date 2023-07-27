package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserMapperService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserService manager;
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final UserMapperService userMapperService;
    private final UserRepository userRepository;


    @Override
    public boolean login(String userName, String password) {
        //        userRepository.findFirstUser(user);
        UserDetails userDetails = manager.loadUserByUsername(userName);
        String encryptedPassword = userDetails.getPassword();
        return encoder.matches(password, encryptedPassword);

    }

    @Override
    public boolean register(RegisterDto registerReqDto) {
        if (userRepository.findUserByUsername(registerReqDto.getUsername()).isPresent()) {
            return false;
        }
        User regUser = userMapperService.mapToUser(registerReqDto);
        regUser.setRole(registerReqDto.getRole());
        regUser.setPassword(encoder.encode(regUser.getPassword()));
        userRepository.save(regUser);
        return true;
    }
}
