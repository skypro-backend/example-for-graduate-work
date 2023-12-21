package ru.skypro.homework.service;

import ru.skypro.homework.model.User;

public interface UserService {

    User findById(Long id);

    User setPassword(User user);

    User updateMyProfile(User user);

    void setImage();
}
