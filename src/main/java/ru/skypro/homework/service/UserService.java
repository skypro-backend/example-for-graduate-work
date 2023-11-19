package ru.skypro.homework.service;

import ru.skypro.homework.model.User;

public interface UserService {

    User get(Long id);

    User update(Long id, User user);


    User setPassword(User user);
}
