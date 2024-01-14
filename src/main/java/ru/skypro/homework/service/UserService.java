package ru.skypro.homework.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

import javax.transaction.Transactional;

public class UserService {
    void setPassword(NewPassword newPassword, Authentication authentication);

    UserDto getUser(Authentication authentication);

    UpdateUser updateUserInfo(UpdateUser update, Authentication authentication);

    @Transactional
    void updateUserAvatar(MultipartFile image, Authentication authentication) {

    }
}
