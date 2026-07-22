package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {

    User getUser();

    User updateUser(UpdateUser updateUser);

    void updateImage(MultipartFile image);

    void updatePassword(NewPassword newPassword);
}