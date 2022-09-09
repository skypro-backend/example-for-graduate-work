package ru.skypro.homework.service;

import ru.skypro.homework.models.dto.CreateUserDto;
import ru.skypro.homework.models.dto.NewPasswordDto;
import ru.skypro.homework.models.dto.UserDto;

import java.util.List;

public interface UserService {

    CreateUserDto addUser(CreateUserDto user);

    List<UserDto> getUsers();

    UserDto updateUser(UserDto user);

    NewPasswordDto setPassword(NewPasswordDto newPassword);

    UserDto getUser(Integer id);

}
