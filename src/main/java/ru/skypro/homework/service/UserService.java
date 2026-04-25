package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {
    User getUser(String email);
    User updateUser(String email, UpdateUser updateUser);
    void updatePassword(String email, NewPassword newPassword);
    void updateImage(String email, byte[] image);
}