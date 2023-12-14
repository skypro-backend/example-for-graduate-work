package ru.skypro.homework.service;

import ru.skypro.homework.dto.users.UpdateUser;
import ru.skypro.homework.dto.users.User;

public interface UserService {
    User getLoggedInUser();

    User updateUserDetails(UpdateUser updateUser);
}
