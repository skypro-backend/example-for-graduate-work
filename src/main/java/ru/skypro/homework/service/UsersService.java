package ru.skypro.homework.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

public interface UsersService {

    void setPassword(String currentPassword, String newPassword, String username);

    Optional<UserEntity> getUser(String username);

    UpdateUser updateUser(UpdateUser updateUser, String username);

    void updateUserImage(MultipartFile file, String username);

    void createUser(UserDetails userDetails);

    boolean userExists(String username);

    Optional<UpdateUser> updateUser(String name, UpdateUser userUpdate);
}
