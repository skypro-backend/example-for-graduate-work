package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDto;

public interface AdService {
    boolean login(String userName, String password);

    boolean register(RegisterDto registerDto);
}
