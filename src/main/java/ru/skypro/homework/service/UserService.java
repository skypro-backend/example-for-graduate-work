package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;

public interface UserService {

    void updatePassword(String userName, NewPasswordDto newPasswordDto);

    UserDto getUser(String userName);

    UpdateUserDto updateUser(String userName,UpdateUserDto updateUserDto);

    void updatePhoto(String userName, MultipartFile image);
}
