package ru.skypro.homework.service.auth;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.entity.User;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);

    void createUser(Register register);
}
