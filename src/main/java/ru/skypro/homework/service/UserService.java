package ru.skypro.homework.service;

import ru.skypro.homework.dto.user.User;

import java.util.List;

public interface UserService {

    User findUserById(int userId);

    User getUsers(String email);

    User getUserById(Integer id);

    List<User> allUsers();

    User addUser(User user);

    public void deleteUser(Integer userId);


}
