package ru.skypro.homework.service;

import ru.skypro.homework.entity.UserEntity;

public interface UserCRUDService {
    UserEntity createUser(String username, String password) throws Exception;
    UserEntity getUser(String username);
    UserEntity updateUser(UserEntity user);
    void deleteUser(String username);
}
