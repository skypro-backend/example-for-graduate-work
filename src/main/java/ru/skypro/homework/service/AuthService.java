package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
/**
 * The interface with methods to login and register users
 */
public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
