package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AvatarDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.model.Images;
import ru.skypro.homework.model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<UserDto> users = new ArrayList<>();
    UserDto saveUser(UserDto user);
    UserDto deleteUser(UserDto user);
    UserDto getInfoUser();
    UpdateUserDto setInfoUser(UpdateUserDto updateUser);
    AvatarDto setAvatar(MultipartFile avatar) throws IOException;


    String saveToDisk(Long studentId, MultipartFile multipartFile) throws IOException;

    Images saveToDb(Integer userId, MultipartFile multipartFile, String absolutePath) throws IOException;
}
