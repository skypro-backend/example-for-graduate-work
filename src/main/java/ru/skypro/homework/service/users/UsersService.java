package ru.skypro.homework.service.users;

import ru.skypro.homework.dto.auth.RegisterDto;

public interface UsersService {
    void register(RegisterDto registerDto);
}
