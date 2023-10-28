package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.User;

public interface UserService {
    User find();

    void createUser(RegisterDto registerDto);

    UserDto getUser();

    void updatePassword(NewPasswordDto newPasswordDto);

    void updatePassword(NewPasswordDto newPasswordDto, String username);

    void updateUser(UpdateUserDto updateUserDto);

    void update(MultipartFile image);
    void saveUserAvatar(MultipartFile avatar);

    void saveUserAvatar(Authentication authentication, MultipartFile image);
}