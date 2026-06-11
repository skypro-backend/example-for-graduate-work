package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;
import ru.skypro.homework.model.User;

public interface UserService {

    User getUserByEmail(String email);

    UserDto getUser(String email);

    UpdateUserDto updateUser(String email, UpdateUserDto updateUserDto);

    void updateUserImage(String email, MultipartFile image);

    void changePassword(String email, NewPasswordDto newPasswordDto);
}
