package ru.skypro.homework.service;

import org.hibernate.SQLQuery;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto registerDto, RoleDto roleDto);
}


