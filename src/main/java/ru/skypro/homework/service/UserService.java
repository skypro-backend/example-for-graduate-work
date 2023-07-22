package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getAllUsers();

    public User getUser(int userId) ;

    void addUser(RegisterReq registerReq);

    void addUser(User user);

    User findUserById(int userId);

    User findUserByUserName(String username);

    void updateUser(UserDTO userDTO);

    boolean userExists(String userName);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
