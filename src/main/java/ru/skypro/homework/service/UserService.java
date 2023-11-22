package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {

    UserDTO getCurrentUser();

    UpdateUserDTO updateUser(UpdateUserDTO updateUserDTO);

    Void setPassword(NewPasswordDTO newPasswordDTO);

    Void updateUserImage(MultipartFile image);
}