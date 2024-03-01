package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.User;

public interface AuthService {
    void login(String userName, String password);

    User register(Register register);
}
