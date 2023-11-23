package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

import java.io.File;
import java.io.IOException;

public interface UserService {

    UserDTO getCurrentUser();

    UserDTO updateUser(UpdateUserDTO updateUserDTO);

    Void setPassword(NewPasswordDTO newPasswordDTO);

    String updateUserImage(MultipartFile image) throws IOException;
}