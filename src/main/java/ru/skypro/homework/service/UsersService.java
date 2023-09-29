package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UsersService {

    void setPassword(String currentPassword, String newPassword);

    User getUser();

    UpdateUser updateUser(UpdateUser updateUser);

    void updateUserImage(MultipartFile file);

}
