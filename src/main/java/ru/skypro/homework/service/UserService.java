package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface UserService {

    User findById(Long id);

    User setPassword(User user);

    User updateMyProfile(User user);

    Image setImage(Long id, MultipartFile file) throws IOException;
}
