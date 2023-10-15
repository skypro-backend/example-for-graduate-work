package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.model_dto.UserDto;
import ru.skypro.homework.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

      void newPassword (String newPassword , String currentPassword);

      Optional <User> findAuthUser ();

      // обновление информации об авторизованном пользователе
      UserDto updateUserDto (UserDto newUserDto);
}
