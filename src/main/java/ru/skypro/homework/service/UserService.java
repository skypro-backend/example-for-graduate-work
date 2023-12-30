package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.User;

public interface UserService {

    /**
     * Получение информации об авторизованном пользователе. Права имеет только аутентифицированный пользователь.
     * @param authentication пользователь авторизованной сессии
     * @return UserDto - dto инфо о юзере
     */

    UserDTO getUser(Authentication authentication);

    /**
     * Обновление пароля. Права имеет только аутентифицированный пользователь.
     * @param newPasswordDTO - dto содержащий новый пароль
     * @param authentication пользователь авторизованной сессии
     */

    void setPassword(NewPasswordDTO newPasswordDTO,Authentication authentication);

    /**
     * Обновление информации об авторизованном пользователе. Права имеет только аутентифицированный пользователь.
     * @param updateUserDTO - dto для обновления юзера
     * @param authentication пользователь авторизованной сессии
     * @return UpdateUserDto - dto для обновления юзера
     */

    UpdateUserDTO updateUserInfo(UpdateUserDTO updateUserDTO, Authentication authentication);

    /**
     * Обновление аватара авторизованного пользователя. Права имеет только аутентифицированный пользователь.
     * @param image полученный MultipartFile от фронта
     * @param authentication пользователь авторизованной сессии
     */

    void updateUserAvatar(MultipartFile image, Authentication authentication);

}
