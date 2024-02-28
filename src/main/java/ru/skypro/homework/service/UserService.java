package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entities.UserEntity;

import java.io.IOException;

public interface UserService {

    void setPassword(NewPassword newPass, Authentication authentication);
    UserEntity getUser(String userName);
    UserEntity updateUser(UpdateUser updateUser, Authentication authentication);
    void updateUserImage(MultipartFile image, Authentication authentication) throws IOException;
}
