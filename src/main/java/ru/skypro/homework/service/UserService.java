package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;

import ru.skypro.homework.model.User;


import java.io.IOException;

public interface UserService {

    UserDTO getCurrentUser();

    User getCurrentUser(String userName);

    UserDTO updateUser(UpdateUserDTO updateUserDTO);

    Void setPassword(NewPasswordDTO newPasswordDTO);


    Void updateUserImage(MultipartFile image);

}