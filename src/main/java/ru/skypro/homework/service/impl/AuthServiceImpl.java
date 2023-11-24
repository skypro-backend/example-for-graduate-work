package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.mapper.RegisterMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final PasswordEncoder encoder;
    private final UserRepository userRepository;
    private final UserServiceImpl userService;


    /**
     * Авторизация пользователя
     *
     * @return {@link PasswordEncoder#matches(CharSequence, String)}
     */
    @Override
    public boolean login(String userName, String password) {
        if (!userRepository.findByUserName(userName).isPresent()) {
            return false;
        }
        UserDetails userDetails = userService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    /**
     * Регистрация пользователя в системе:
     * {@link UserRepository#findByUserName(String)}
     * {@link RegisterMapper#toModel(RegisterDto)}
     *
     * @return {@link UserRepository#save(Object)}
     */
    @Override
    public boolean register(RegisterDto register, Role role) {
        if (userRepository.findByUserName(register.getUsername()).isPresent()) {
            return false;
        }
        User user = RegisterMapper.INSTANCE.toModel(register);
        user.setPassword(encoder.encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

}
