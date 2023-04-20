package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReqDTO;
import ru.skypro.homework.dto.Role;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReqDTO registerReq, Role role);
}
