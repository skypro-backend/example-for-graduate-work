package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    void setPassword(String currentPassword, String newPassword);

    UserDto getAuthorizedUser();

    UpdateUserDto updateUser(UpdateUserDto updateUser);

    void UpdateUserImage(MultipartFile file);
}