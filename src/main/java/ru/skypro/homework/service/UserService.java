package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    User getUser(String username);
    UpdateUser updateUser(String username, UpdateUser updateUser);
    boolean setPassword(String username, NewPassword newPassword);
    String updateUserImage(String username, MultipartFile image);
}
