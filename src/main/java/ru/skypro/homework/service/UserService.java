package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

public interface UserService {

    UserDTO getUser(Authentication authentication);

    void setPassword(NewPasswordDTO newPasswordDTO,Authentication authentication);

    UpdateUserDTO updateUserInfo(UpdateUserDTO updateUserDTO, Authentication authentication);

    void updateUserAvatar(MultipartFile image, Authentication authentication);

}
