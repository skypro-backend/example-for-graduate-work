package ru.skypro.homework.service;

import ru.skypro.homework.dto.RegisterReqDto;
import ru.skypro.homework.dto.RoleEnum;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReqDto registerReqDto, RoleEnum roleEnum);
}
