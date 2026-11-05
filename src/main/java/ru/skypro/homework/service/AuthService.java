package ru.skypro.homework.service;

import ru.skypro.homework.dto.users.Login;
import ru.skypro.homework.dto.users.Register;

public interface AuthService {
    void login(Login login);

    void register(Register register);
}
