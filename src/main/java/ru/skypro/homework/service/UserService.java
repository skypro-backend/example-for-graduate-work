package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

public interface UserService {
    User get(Integer id);

    UpdateUser updateUser(Integer id, UpdateUser updateUser);
    NewPassword setPassword (Integer id, NewPassword newPassword);
}
