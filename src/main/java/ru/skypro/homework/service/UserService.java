package ru.skypro.homework.service;

import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    UserDto addUser(CreateUser createUser);

    ResponseWrapperUser getUsers();

    UserDto updateUser(UserDto userDto);

    UserDto getUser(int id);
}
