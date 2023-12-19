package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.RegisterDTO;
import ru.skypro.homework.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDTO register, Role role);

    void updatePassword(NewPasswordDTO newPassword);
}
