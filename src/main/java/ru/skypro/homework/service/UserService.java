package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
@Service
public interface UserService {

    UpdateUserDto update(UpdateUserDto updateUserDto, Authentication authentication);

    NewPasswordDto setPassword(NewPasswordDto newPasswordDto, Authentication authentication);

    UserDto getUserDto(Authentication authentication);

    String updateImage(MultipartFile image, Authentication authentication);

}
