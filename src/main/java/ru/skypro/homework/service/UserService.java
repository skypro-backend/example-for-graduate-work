package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Avatar;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface UserService {

    List<UserDto> users = new ArrayList<>();
    UserDto saveUser(UserDto user);
    UserDto deleteUser(UserDto user);
    UserDto getInfoUser();
    UpdateUser setInfoUser(UpdateUser updateUser);
    Avatar setAvatar(MultipartFile avatar) throws IOException;


}
