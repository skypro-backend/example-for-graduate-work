package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface UserService {

    UserDTO findUser(Authentication authentication);
    UpdateUser editUser(UpdateUser updateUserDto, Authentication authentication);
    String editUserImage(MultipartFile image,
                         Authentication authentication) throws IOException;
    boolean setPassword(NewPassword newPassword, Authentication authentication);

}
