package ru.skypro.homework.service.auth;

import ru.skypro.homework.dto.auth.RegisterDto;

public interface AuthService {
    boolean register(RegisterDto registerDto);

    boolean login(String userName, String password);
}
