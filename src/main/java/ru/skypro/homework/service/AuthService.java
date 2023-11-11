package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);

    boolean setPassword(NewPasswordDto newPassword, String name);

    boolean register(Register registerReq, Role role);
}
