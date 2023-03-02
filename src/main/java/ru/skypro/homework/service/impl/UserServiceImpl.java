package ru.skypro.homework.service.impl;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@Service
public class UserServiceImpl {

    /**
     * Метод добавления пользователя
     *
     * @param user - пользователь на добавление
     * @return возвращает добавленного пользователя
     */
    public CreateUser addUser(CreateUser user) {
        return null;
    }

    /**
     * Метод получения всех пользователей
     */
    public User getUsers() {
        return null;
    }

    /**
     * Метод изменения пользователя
     *
     * @param user - пользователь на изменение
     * @return возвращает обновленного пользователя
     */
    public User updateUser(User user) {
        return null;
    }

    /**
     * Метод установки пароля
     *
     * @param password - новый пароль
     * @return возвращает установленный пароль
     */
    public NewPassword setPassword(NewPassword password) {
        return null;
    }

    /**
     * Метод получения пользователя
     *
     * @param id - ID пользователя
     * @return возвращает найденного пользователя
     */
    public User getUser(Integer id) {
        return null;
    }
}
