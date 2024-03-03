package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.User;

/**
 * Service for working with auth.
 */
public interface AuthService {
    /**
     * Authorizes the user by username and password.
     *
     * @param userName username
     * @param password password
     */
    void login(String userName, String password);

    /**
     * Registers the user.
     *
     * @param register info about user
     * @return created user
     */
    User register(Register register);
}
