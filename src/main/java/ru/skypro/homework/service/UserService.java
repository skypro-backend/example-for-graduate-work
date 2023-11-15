package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {

    UserDTO getCurrentUser();

    UserDTO updateUser(UserDTO user);

    UserDTO setUserPassword(NewPasswordDTO passwordDto);
    UserDTO loadUserImage(MultipartFile image);
}