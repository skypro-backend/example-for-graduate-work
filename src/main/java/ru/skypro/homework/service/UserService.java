package ru.skypro.homework.service;

import ru.skypro.homework.models.User;

public interface UserService {

    User create(User user);
    User read(Integer id);
    User update(User user);
    User delete(Integer id);
}
