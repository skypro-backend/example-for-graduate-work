package ru.skypro.homework.service;

import ru.skypro.homework.dto.Registred;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(Registred register);
}
