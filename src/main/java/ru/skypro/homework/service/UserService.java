package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.io.IOException;

public interface UserService {

    void setPassword(NewPassword newPassword, UserDetails userDetails);

    User getUser(UserDetails userDetails);

    UpdateUser updateUser(UpdateUser updateUser, UserDetails userDetails);

    void updateImage(MultipartFile image, UserDetails userDetails) throws IOException;
}
