package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.pojo.User;

import java.util.Optional;

public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register);

    Optional<String> changePassword(String username, String currentPassword, String newPassword);

    User updateUserInfo(Long userId, UpdateUserDTO updateUserDTO);


}
