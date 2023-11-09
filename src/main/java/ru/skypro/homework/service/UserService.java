package ru.skypro.homework.service;

import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    UserDto getCurrentUser();

    UserDto updateUser(UserDto user);

    UserDto setUserPassword(SetPasswordDto passwordDto);
}