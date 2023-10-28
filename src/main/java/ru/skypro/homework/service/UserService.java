package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.NewPasswordDto;
import ru.skypro.homework.dto.model_dto.UpdateUserDto;
import ru.skypro.homework.dto.model_dto.UserDto;

import java.io.IOException;

public interface UserService {


      void newPassword (NewPasswordDto newPasswordDto ,  Authentication authentication); // обновление пароля

      UserDto findAuthUser (Authentication authentication); // получение информации об авторизованном пользователе

      UpdateUserDto updateUser (UpdateUserDto updateUserDto , Authentication authentication); //  обновление информации об авторизованном пользователе

     void updateAvatar (MultipartFile multipartFile , Authentication authentication) throws IOException; // обновление изображения пользователя.
}
