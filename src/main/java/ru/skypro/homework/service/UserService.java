package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    UserDTO getCurrentUser();

    UserDTO updateUser(UpdateUserDTO updateUserDTO);

    Void setPassword(NewPasswordDTO newPasswordDTO);

    void updateUserImage(MultipartFile image, String userName);
}
