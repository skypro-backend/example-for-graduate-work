package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

    UserDto getUser();

    UpdateUserDto updateUser(UpdateUserDto updateUser);

    void updateUserImage(MultipartFile file);

    boolean setPassword(NewPasswordDto newPassword);

    byte[] getImage(Integer id);

}
