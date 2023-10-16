package ru.skypro.homework.service;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);

    UserDTO getUser();

    void updatePassword(NewPasswordDTO newPasswordDTO);

    void updateAvatar(MultipartFile image);
}
