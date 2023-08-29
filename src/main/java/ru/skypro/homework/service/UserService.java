package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;
public interface UserService {

    UserDto get(Authentication authentication);

    UserDto update(UserDto userDto, Authentication authentication);

    void updatePassword(NewPassword newPassword, Authentication authentication);

    void updateAvatar(MultipartFile avatar, Authentication authentication) throws IOException;
}
