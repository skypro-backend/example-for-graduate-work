package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public UserDto getCurrentUser() {
        return null;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        return null;
    }

    @Override
    public UserDto setUserPassword(SetPasswordDto passwordDto) {
        return null;
    }
    @Override
    public UserDto loadUserImage(MultipartFile image) {
        return null;
    }
}