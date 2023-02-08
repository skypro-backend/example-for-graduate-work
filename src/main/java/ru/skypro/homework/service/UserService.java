package ru.skypro.homework.service;

import java.util.Collection;
import ru.skypro.homework.dto.User;

public interface UserService {

  Collection<User> getUsers();

  User getUser(int id);

  User updateUser(User user);

  String setPassword(String newPassword, String currentPassword);


}
