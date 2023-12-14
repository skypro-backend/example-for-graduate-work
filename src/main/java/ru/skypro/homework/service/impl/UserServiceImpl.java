package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.users.UpdateUser;
import ru.skypro.homework.dto.users.User;
import ru.skypro.homework.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public User getLoggedInUser() {
        return null;
    }

    @Override
    public User updateUserDetails(UpdateUser updateUser) {
        return null;
    }
}
