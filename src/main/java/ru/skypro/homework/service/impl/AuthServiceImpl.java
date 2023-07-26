package ru.skypro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserDetailsManager manager;
    private final PasswordEncoder encoder;
    private final UserService userService;

    @Override
    public boolean login(String userName, String password) {

        if (!manager.userExists(userName)) {
            return false;
        }

        UserDetails userDetails = manager.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());

    }

    @Override
    public boolean register(Register register) {

        if (manager.userExists(register.getUsername())) {
            return false;
        }

        manager.createUser(
                User.builder()
                        .passwordEncoder(this.encoder::encode)
                        .password(register.getPassword())
                        .username(register.getUsername())
                        .roles(register.getRole().name())
                        .build());

        userService.registerNewUser(register, this.encoder.encode(register.getPassword()));

        return true;

    }

    @Override
    public boolean changePassword(String userLogin, NewPassword newPassword) {

        if (!(checkPassword(userLogin, newPassword.getCurrentPassword()))) {
            return false;
        }

        Optional<ru.skypro.homework.entity.User> optionalUser = userService.getUserByLogin(userLogin);

        if (optionalUser.isEmpty()) {
            return false;
        }

        ru.skypro.homework.entity.User user = optionalUser.get();

        user.setPassword(encoder.encode(newPassword.getNewPassword()));
        Role role = user.getRole();

        manager.updateUser(User.builder()
                .password(user.getPassword())
                .username(user.getEmail())
                .roles(role.name())
                .build());

        userService.updatePassword(user);

        return true;

    }

    private boolean checkPassword(String userLogin, String password) {
        return encoder.matches(password, manager.loadUserByUsername(userLogin).getPassword());
    }

}
