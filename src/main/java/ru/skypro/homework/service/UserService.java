package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.User;

import java.io.IOException;
public interface UserService {
    UpdateUserDto update(UpdateUserDto updateUser, String name);

    void delete(String name);

    UserDto get(String name);

    User getEntity(String name);

    User getEntityById(int id);

    void changePassword(String newPassword, String name);

    boolean userExists(String username);

    void createUser(User user);

    void uploadImage(MultipartFile image, String name) throws IOException;
}
