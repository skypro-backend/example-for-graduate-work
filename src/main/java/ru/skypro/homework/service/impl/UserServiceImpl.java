package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;
@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDTO getLoggedInUser() {
        return null;
    }

    @Override
    public UserDTO updateUserDetails(UpdateUser updateUser) {
        return null;
    }
}
