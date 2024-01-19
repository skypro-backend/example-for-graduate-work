package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.config.MyUserDetailsService;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.exception.UserAlreadyAddException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

import javax.validation.constraints.NotNull;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final MyUserDetailsService userDetailsService;
    private final UserRepository repository;
    private final PasswordEncoder encoder;
    private final UserMapper mapper;


    @Override
    public boolean login(@NotNull String userName, @NotNull String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        if (encoder.matches(password,userDetails.getPassword())){
            return true;
        }
        throw new BadCredentialsException("Неверный логин или пароль");
    }

    @Override
    public boolean register(Register register) {
        User user = mapper.registerToUser(register);
        if (repository.existsUserByEmailIgnoreCase(user.getEmail())){
            throw new UserAlreadyAddException("Пользователь уже добавлен!");
        }
        user.setPassword(encoder.encode(register.getPassword()));
        repository.save(user);
        return true;
    }

}
