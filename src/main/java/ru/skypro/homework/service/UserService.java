package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    UserDto getCurrentUser();

    UserDto updateUser(UserDto user);

    UserDto setUserPassword(NewPassword passwordDto);
    UserDto loadUserImage(MultipartFile image);
}