package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;


    @Override
    public boolean setPassword(User user, NewPassword newPassword) {
        if(passwordEncoder.matches(newPassword.getCurrentPassword(), user.getPassword())) {
            String password = passwordEncoder.encode(newPassword.getNewPassword());
            user.setPassword(password);
            userRepository.save(user);
            return true;
        } else
            return false;
    }
}
