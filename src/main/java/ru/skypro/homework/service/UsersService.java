package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UsersService {

    void setPassword(String currentPassword, String newPassword, String username);

    User getUser(String username);

    UpdateUser updateUser(UpdateUser updateUser, String username);

    byte[] updateUserImage(MultipartFile file, String username);

    void createUser(UserDetails userDetails);

    boolean userExists(String username);

}
