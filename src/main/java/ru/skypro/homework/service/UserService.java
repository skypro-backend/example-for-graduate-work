package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

/**
 * Сервис для работы с пользователями
 */
public interface UserService {

    /**
     * Получение пользователя
     *
     * @return ДТО пользователя
     */
    UserDto getUser();

    /**
     * Редактирование пользователя
     *
     * @param updateUser ДТО пользователя
     * @return Отредактированный пользователь
     */
    UpdateUserDto updateUser(UpdateUserDto updateUser);

    /**
     * Редактирование аватарки пользователя
     *
     * @param file Файл аватарки
     */
    void updateUserImage(MultipartFile file);

    /**
     * Установление пароля
     *
     * @param newPassword ДТО с текущим и новым паролем
     * @return Пароль установился или нет
     */
    boolean setPassword(NewPasswordDto newPassword);

    /**
     * Получение аватарки по id пользователя
     *
     * @param id id пользователя
     * @return Массив байт
     */
    byte[] getImage(Integer id);

}
