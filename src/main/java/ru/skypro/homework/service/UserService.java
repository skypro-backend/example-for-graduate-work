package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    boolean setPassword(NewPassword newPassword, String username);
    User getUser(String username);
    UpdateUser updateUser(UpdateUser updateUser, String username);
    void updateUserImage(MultipartFile image, String username);
}