package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {

    User getUser(Authentication authentication);

    UpdateUser updateUser(UpdateUser dto, Authentication authentication);

    void updateUserImage(MultipartFile image, Authentication authentication);

    void setPassword(NewPassword dto, Authentication authentication);
}
