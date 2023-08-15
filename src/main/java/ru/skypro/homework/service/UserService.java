package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDto;

public interface UserService {
    UserDto getUser();
    UserDto updateUserDto(UserDto userDto);
    Void updateUserImage (MultipartFile image);
}
