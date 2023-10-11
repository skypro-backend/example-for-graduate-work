package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);

    UserDTO getUser();

    void updatePassword(NewPasswordDTO newPasswordDTO);
}
