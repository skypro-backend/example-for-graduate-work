package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UpdateUserService {
    UpdateUserDto update(Integer id, UpdateUserDto updateUserDto);
}
