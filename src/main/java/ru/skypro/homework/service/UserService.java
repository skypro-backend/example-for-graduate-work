package ru.skypro.homework.service;

import org.springframework.security.access.prepost.PreAuthorize;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);
    @PreAuthorize("hasRole('USER')")
    UserDTO getUser();

    void updatePassword(NewPasswordDTO newPasswordDTO);
}
