package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterDto;

public interface RegisterService {
    boolean registerUser(RegisterDto register);
}
