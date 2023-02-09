package ru.skypro.homework.service;

import java.util.Collection;
import ru.skypro.homework.dto.UserDto;

public interface UserService {

  Collection<UserDto> getUsers();

  UserDto getUser(int id);

  UserDto updateUser(UserDto userDto);

  String setPassword(String newPassword, String currentPassword);


}
