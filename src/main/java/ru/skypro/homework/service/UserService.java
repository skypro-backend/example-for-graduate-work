package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    UserDTO getUser(String userName);
    UserDTO updateUser(String userName, UpdateUser updateUser);
}

