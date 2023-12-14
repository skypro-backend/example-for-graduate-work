package ru.skypro.homework.service.impl;

import ru.skypro.homework.dto.Avatar;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {
    void setPassword(String currentPassword, String confirmPhoneNumber, String newPassword);
    List<User> users = new ArrayList<>();
    User saveUser(User user);
    User deleteUser(User user);
    UpdateUser setInfoUser(UpdateUser updateUser);
    Avatar setAvatar(Avatar avatar);

}
