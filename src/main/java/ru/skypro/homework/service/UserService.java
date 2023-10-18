package ru.skypro.homework.service;

import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;

public interface UserService {
    void updatePassword(NewPassword newPassword);
    User getInformation();
    UpdateUser updateInformationAboutUser(UpdateUser updateUser);
    void UpdateImage(String image);
}