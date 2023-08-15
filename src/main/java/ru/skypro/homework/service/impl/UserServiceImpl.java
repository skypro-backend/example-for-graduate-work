package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public UserDto getUser() {
        return null;
    }

    @Override
    public UserDto updateUserDto(UserDto userDto) {
        return null;
    }

    @Override
    public Void updateUserImage(MultipartFile image) {
        return null;
    }
}
