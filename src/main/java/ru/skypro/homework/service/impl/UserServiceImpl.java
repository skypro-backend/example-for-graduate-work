package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.service.UserService;
import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUserDetails() {
        return null;
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUserInfo) throws Exception {
        return null;
    }

    @Override
    public void updatePassword(String currentPassword, String newPassword) throws Exception {
    }

    @Override
    public void updateUserImage(byte[] imageBytes) throws IOException {
    }
}
