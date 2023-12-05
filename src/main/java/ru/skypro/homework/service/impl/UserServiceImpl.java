package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;
@Service
public class UserServiceImpl implements UserService {

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public UpdateUser updateUser(UpdateUser updateUser) {
        return null;
    }

    @Override
    public void updateUserImage(MultipartFile file) {

    }

    @Override
    public boolean setPassword(NewPassword newPassword) {
        return false;
    }

    @Override
    public byte[] getImage(Integer id) {
        return new byte[0];
    }
}
