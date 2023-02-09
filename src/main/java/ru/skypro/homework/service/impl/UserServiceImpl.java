package ru.skypro.homework.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {


  @Override
  public UserDto addUser(UserDto userDto) {
    return null;
  }

  @Override
  public Collection<UserDto> getUsers() {
    return null;
  }

  @Override
  public UserDto getUser(int id) {
    return null;
  }

  @Override
  public UserDto updateUser(UserDto userDto) {
    return null;
  }

  @Override
  public String setPassword(String newPassword, String currentPassword) {
    return null;
  }


}
