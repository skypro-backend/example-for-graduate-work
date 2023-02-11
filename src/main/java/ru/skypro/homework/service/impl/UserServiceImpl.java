package ru.skypro.homework.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {


  @Override
  public UserDTO addUser(UserDTO userDto) {
    return null;
  }

  @Override
  public Collection<UserDTO> getUsers() {
    return null;
  }

  @Override
  public UserDTO getUser(int id) {
    return null;
  }

  @Override
  public UserDTO updateUser(UserDTO userDto) {
    return null;
  }

  @Override
  public String setPassword(String newPassword, String currentPassword) {
    return null;
  }


}
