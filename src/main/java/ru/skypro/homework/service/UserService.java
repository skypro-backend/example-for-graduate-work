package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.RegisterReq;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.UserUpdateReq;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.model.User;

import javax.transaction.Transactional;
import java.util.List;

public interface UserService {
    List<User> getAllUsers();

    UserDTO getUser() throws UserNotFoundException;

    public User getUser(int userId) ;

    void addUser(RegisterReq registerReq);

    void addUser(User user);

    User findUserById(int userId);

    User findUserByUserName(String username);

    void updateUser(UserDTO userDTO);

    void updateUser(UserUpdateReq req) throws UserNotFoundException;

    @Transactional
    boolean updateUserPassword(NewPassword passwordDTO);

    boolean userExists(String userName);

    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    boolean updateUserImage(MultipartFile file) throws UserNotFoundException;

    User getAuthUser();
}
