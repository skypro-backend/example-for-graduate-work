package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean setPassword(NewPasswordDto newPassword, String name);

    boolean register(RegisterDto register);
}
