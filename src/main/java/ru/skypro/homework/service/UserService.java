package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
    void setPassword(NewPasswordDTO newPassword);

    UserDTO getUserInfo();

    UpdateUserDTO updateUser(UpdateUserDTO updateUser);

    void updateUserImage(MultipartFile image);

    byte[] getImage(String id);

}
