package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skypro.homework.dto.users.Login;
import ru.skypro.homework.dto.users.Register;
import ru.skypro.homework.exception.BadRequestException;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.AuthService;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void login(Login login) {
        User user = userRepository.findByEmail(login.getUsername())
                        .orElseThrow(() -> new BadRequestException("Неверный логин или пароль"));
        if(!passwordEncoder.matches(login.getPassword(), user.getPassword())){
            throw new BadRequestException("Неверный логин или пароль");
        }
    }


    @Override
    @Transactional
    public void register(Register register) {
        if (userRepository.findByEmail(register.getUsername()).isPresent()) {
            throw new BadRequestException("Пользователь с таким email уже существует");
        }

        User user = userMapper.toEntity(register);

        user.setPassword(passwordEncoder.encode(register.getPassword()));

        if (user.getRole() == null) {
            user.setRole(register.getRole());
        }

        userRepository.save(user);
    }

}
