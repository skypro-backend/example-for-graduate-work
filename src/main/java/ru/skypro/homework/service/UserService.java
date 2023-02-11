package ru.skypro.homework.service;

import java.util.Collection;
import ru.skypro.homework.dto.UserDTO;

public interface UserService {
  UserDTO addUser(UserDTO userDto);

  Collection<UserDTO> getUsers();

  UserDTO getUser(int id);

  UserDTO updateUser(UserDTO userDto);

  String setPassword(String newPassword, String currentPassword);


}
