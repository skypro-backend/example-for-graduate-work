package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;

public interface UserService {
    void updatePassword(NewPassword newPassword, Integer id);
    User getInformation(Integer id);
    UpdateUser updateInformationAboutUser(UpdateUser updateUser, Integer id);
    void UpdateImage(MultipartFile file, Integer id);
}