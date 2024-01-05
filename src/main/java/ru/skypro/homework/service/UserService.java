package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AvatarDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<UserDto> users = new ArrayList<>();
    UserDto saveUser(UserDto user);
    UserDto deleteUser(UserDto user);

    UserDto getInfoUser(String userName);
    UpdateUserDto setInfoUser(UpdateUserDto updateUser);
    AvatarDto setAvatar(MultipartFile avatar) throws IOException;


}
