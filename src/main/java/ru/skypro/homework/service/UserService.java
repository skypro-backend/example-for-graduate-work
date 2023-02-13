package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;

/**
 * сервис пользователя
 */
public interface UserService {

  /**
   * получить пользователя
   */
  UserDTO getUser();

  /**
   * обновить пользователя
   */
  UserDTO updateUser(UserDTO userDto) ;

  /**
   * установить новый пароль пользователя
   */
  NewPasswordDTO setPassword(NewPasswordDTO newPassword);

  /**
   * обновить фото пользователя
   */
  byte[] updateUserImage(MultipartFile image);
}
