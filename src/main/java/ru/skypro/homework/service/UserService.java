package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDto;

import javax.transaction.Transactional;
import java.io.IOException;

public interface UserService {
    void setPassword(NewPassword newPassword, Authentication authentication);

    UserDto getUser(Authentication authentication);

    UpdateUser updateUserInfo(UpdateUser update, Authentication authentication);

    @Transactional
    void updateUserAvatar(MultipartFile image, Authentication authentication) throws IOException ;

    }

