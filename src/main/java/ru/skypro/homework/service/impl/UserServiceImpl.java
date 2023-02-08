package ru.skypro.homework.service.impl;

import java.util.Collection;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@Service
public class UserServiceImpl implements UserService {


  @Override
  public Collection<User> getUsers() {
    return null;
  }

  @Override
  public User getUser(int id) {
    return null;
  }

  @Override
  public User updateUser(User user) {
    return null;
  }

  @Override
  public String setPassword(String newPassword, String currentPassword) {
    return null;
  }


}
