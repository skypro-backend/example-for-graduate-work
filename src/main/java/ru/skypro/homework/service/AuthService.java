package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.model.Role;

import java.util.Optional;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReq registerReq, Role role);

}
