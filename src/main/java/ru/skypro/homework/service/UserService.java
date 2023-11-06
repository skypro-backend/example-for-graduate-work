package ru.skypro.homework.service;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

public interface UserService {

    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param newPasswordDto {@link NewPasswordDto} объект, содержащий текущий и новый пароли
     * @param authentication {@link Authentication}
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    boolean setPassword(NewPasswordDto newPasswordDto, Authentication authentication);

    /**
     * Получение информации об зарегистрированном пользователе
     *
     * @param authentication {@link Authentication}
     * @return Объект {@link UserDto}
     */
    UserDto getUser(Authentication authentication);

    /**
     * Изменение информации об зарегистрированном пользователе
     *
     * @param userDto        новая информация об пользователе
     * @param authentication {@link Authentication}
     * @return {@link UserDto} обновленные данные, в случае успешного изменения
     */
    UserDto updateUser(UserDto userDto, Authentication authentication);

    /**
     * Импортирует изображение для аватарки зарегистрированном пользователя
     *
     * @param imageFile      объект {@link MultipartFile}
     * @param authentication {@link Authentication}
     * @return <code>true</code> если изображение загружено, <code>false</code> в случае неудачи
     */
    boolean updateUserImage(MultipartFile imageFile, Authentication authentication);

}
