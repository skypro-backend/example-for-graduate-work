package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;

public interface UserService {
    UserDto getUser(String email);
    UpdateUserDto updateUser(String email, UpdateUserDto updateUserDto);
    void updateUserImage(String email, MultipartFile image);
    void setPassword(String email, NewPasswordDto newPasswordDto);
}
