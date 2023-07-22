package ru.skypro.homework.service;

import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;
@Service
public interface UserService {
    public User getUser(int userId) ;

    void addUser(RegisterReq registerReq);

    void addUser(User user);

    User findUserById(int userId);

    User findUserByUserName(String username);

    void updateUser(UserDTO userDTO);
}
