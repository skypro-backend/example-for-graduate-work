package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;


import org.hibernate.sql.Update;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.User;


import java.io.IOException;

public interface UserService {
    UserDto getUserDetails();
    UserDto getUser(Authentication authentication);

    UserDto updateUser(UpdateUserDto updateUserDto, Authentication authentication);

    void setPassword (NewPasswordDto newPasswordDto, Authentication authentication);

    void updateUserImage(MultipartFile image, Authentication authentication) throws IOException;
    User findByEmail(String email);

    User createUser(User user);
}
