package ru.skypro.homework.service;

import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;

public interface AuthService {

    boolean login(Login login);
    boolean register(Register registerDto);

}
