package ru.skypro.homework.service;

import ru.skypro.homework.dto.UserDto;

public interface UserService {

    UserDto get(Long id);

    UserDto update(Long id, UserDto userDto);


//    User setPassword(User user);
}
