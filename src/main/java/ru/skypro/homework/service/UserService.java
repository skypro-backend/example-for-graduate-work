package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.model.User;

import java.util.List;

public interface UserService {

      User findUserById (Integer userId);

      User getUsers (String email);

      List <User> allUsers ();

      User addUser (User user);


      boolean deleteUser (Integer userId);
}
