package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.ElemNotFoundChecked;

/**
 * сервис пользователя
 */
public interface UserService {

  /**
   * получить пользователя
   */
  UserDTO getUser(Authentication authentication);

  /**
   * обновить пользователя
   */
  UserDTO updateUser(UserDTO userDto) throws ElemNotFoundChecked;

  /**
   * установить новый пароль пользователя
   */
  NewPassword setPassword(NewPassword newPassword);

  /**
   * обновить фото пользователя
   */
  byte[] updateUserImage(MultipartFile image, Authentication authentication);
}
