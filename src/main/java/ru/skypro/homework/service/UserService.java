package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {
    boolean setPassword(NewPassword newPassword);

    boolean getProfile(User user);

    boolean updateUser(UpdateUser updateUser);

    boolean updateImage(MultipartFile photo);
}
