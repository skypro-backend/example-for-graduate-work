package ru.skypro.homework.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AvatarDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Service
public class UserServiceImpl implements UserService {
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public UserDto saveUser(UserDto user) {
        logger.info("saving user");
        return null;
    }

    @Override
    public UserDto deleteUser(UserDto user) {
        logger.info("deleting user");
        return null;
    }

    @Override
    public UserDto getInfoUser() {
        return null;
    }

    @Override
    public UpdateUserDto setInfoUser(UpdateUserDto updateUser) {
        return null;
    }

    @Override
    public AvatarDto setAvatar(MultipartFile avatar) throws IOException {
        return null;
    }


}
