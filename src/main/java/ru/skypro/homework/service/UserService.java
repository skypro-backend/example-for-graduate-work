package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.SetPasswordDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    UserDto getCurrentUser();

    UserDto updateUser(UserDto user);

    UserDto setUserPassword(SetPasswordDto passwordDto);
    UserDto loadUserImage(MultipartFile image);
}