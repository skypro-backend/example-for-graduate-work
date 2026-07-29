package ru.skypro.homework.service;

import ru.skypro.homework.data.dto.auth.RegisterRequestDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterRequestDto register);
}
