package ru.skypro.flea.service;

import ru.skypro.flea.dto.RegisterDto;

public interface AuthService {

    boolean login(String userName, String password);

    boolean register(RegisterDto register);

}
