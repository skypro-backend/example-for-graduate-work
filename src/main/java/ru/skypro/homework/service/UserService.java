package ru.skypro.homework.service;


import org.springframework.security.core.Authentication;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;

public interface UserService {
    void register(Register register);

    boolean userExists(String email);

    void changePassword(Authentication authentication, NewPassword newPassword);
}
