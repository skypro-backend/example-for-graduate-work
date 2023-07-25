package ru.skypro.flea.service;

import ru.skypro.flea.dto.Register;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
