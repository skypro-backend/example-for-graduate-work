package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.UserService;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto getUserDetails() {
        return null;
    }

    @Override
    public UpdateUserDto updateUser(UpdateUserDto updateUserInfo) throws Exception {
        return null;
    }

    @Override
    public void updatePassword(String currentPassword, String newPassword) throws Exception {
    }

    @Override
    public void updateUserImage(byte[] imageBytes) throws IOException {
    }
}
