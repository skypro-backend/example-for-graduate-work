package ru.skypro.homework.service;

import ru.skypro.homework.entity.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
