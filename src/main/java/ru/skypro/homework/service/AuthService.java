package ru.skypro.homework.service;

import org.hibernate.SQLQuery;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.RoleDto;

@Service
public interface AuthService {
    boolean login(String userName, String password);

    boolean register(RegisterDto registerDto, RoleDto roleDto);
}


