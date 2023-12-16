package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPassword;


import org.hibernate.sql.Update;
import ru.skypro.homework.dto.*;


import java.io.IOException;

public interface UserService {
    User getUserDetails();

    UpdateUser updateUser(UpdateUser updateUserInfo) throws Exception;

    void updatePassword(String currentPassword, String newPassword) throws Exception;

    void updateUserImage(byte[] imageBytes) throws IOException;
}
