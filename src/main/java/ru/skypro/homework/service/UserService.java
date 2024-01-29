package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    UserDTO getLoggedInUser();

    UserDTO updateUserDetails(UpdateUser updateUser);
}
