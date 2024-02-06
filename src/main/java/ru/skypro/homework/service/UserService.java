package ru.skypro.homework.service;

import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    UserDTO getLoggedInUser();

    UserDTO updateUserDetails(UpdateUserDTO updateUserDTO);
}
