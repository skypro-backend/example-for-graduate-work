package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassDto;
import ru.skypro.homework.dto.UserDto;

public interface CurrentUserService {
    /**
     * Изменение пароля зарегистрированного пользователя
     *
     * @param newPassDto {@link NewPassDto} объект, содержащий текущий и новый пароли
     * @param authentication {@link Authentication}
     * @return <code>true</code> если пароль изменен, <code>false</code> в случае неудачи
     */
    boolean setPassword(NewPassDto newPassDto, Authentication authentication);

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

