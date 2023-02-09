package ru.skypro.homework.service;

import ru.skypro.homework.model.dto.NewPasswordDto;
import ru.skypro.homework.model.dto.RegisterReqDto;
import ru.skypro.homework.model.dto.RoleEnum;

public interface AuthService {
    boolean login(String userName, String password);
    boolean register(RegisterReqDto registerReqDto, RoleEnum roleEnum);
    boolean changePassword(NewPasswordDto newPassword, String name);
}
