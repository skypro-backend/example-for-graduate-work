package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.entity.User;

import java.util.Optional;

public interface UserService {

    UserDTO getUserInfo();

    UserDTO updateInfoUser(UserDTO userDTO);

    Optional<User> findAuthUser();

    void updateUserImage(MultipartFile image);
}
