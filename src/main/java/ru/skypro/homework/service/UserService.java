package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.model.User;

public interface UserService {

    boolean setPassword(User user, NewPassword newPassword);

}
