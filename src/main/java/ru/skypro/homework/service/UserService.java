package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;

public interface UserService {
    boolean updatePassword (NewPassword newPassword);

}
