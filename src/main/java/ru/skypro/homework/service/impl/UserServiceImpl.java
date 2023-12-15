package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Avatar;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public User saveUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(User user) {
        return null;
    }

    @Override
    public User getInfoUser() {
        return null;
    }

    @Override
    public UpdateUser setInfoUser(UpdateUser updateUser) {
        return null;
    }

    @Override
    public Avatar setAvatar(MultipartFile avatar) throws IOException {
        return null;
    }


}
