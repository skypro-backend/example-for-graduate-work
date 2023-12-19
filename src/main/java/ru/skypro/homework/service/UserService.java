package ru.skypro.homework.service;

import ru.skypro.homework.dto.NewPasswordDto;


import org.hibernate.sql.Update;
import ru.skypro.homework.dto.*;


import java.io.IOException;

public interface UserService {
    UserDto getUserDetails();

    UpdateUserDto updateUser(UpdateUserDto updateUserInfo) throws Exception;

    void updatePassword(String currentPassword, String newPassword) throws Exception;

    void updateUserImage(byte[] imageBytes) throws IOException;
}
