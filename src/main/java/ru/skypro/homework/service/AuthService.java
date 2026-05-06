package ru.skypro.homework.service;

import ru.skypro.homework.dto.request.RegisterRequestDto;

public interface AuthService {
    void login(String username, String password);

    void register(RegisterRequestDto register);
}
