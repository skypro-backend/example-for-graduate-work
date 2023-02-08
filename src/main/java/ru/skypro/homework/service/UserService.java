package ru.skypro.homework.service;

import java.util.Collection;
import ru.skypro.homework.dto.User;

public interface UserService {

  Collection<User> getUsers();

  User getUser();

  User updateUser(int id, String firstName, String lastName, String phone, String email);

  String setPassword(String newPassword, String currentPassword);
}
