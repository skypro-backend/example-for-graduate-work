package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.entity.User;

public interface UserService {
    void updatePassword(String username, NewPasswordDto newPasswordDto);

    UserDto getUserInformation(String email);

    UserDto updateUser(String email, UpdateUserDto updateUser);

    void updateUserAvatar (String username, MultipartFile avatar);

}
