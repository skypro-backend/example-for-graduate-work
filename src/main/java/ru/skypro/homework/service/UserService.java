package ru.skypro.homework.service;

import ru.skypro.homework.api.dto.NewPassword;
import ru.skypro.homework.api.dto.UpdateUser;
import ru.skypro.homework.api.dto.User;
import ru.skypro.homework.db.model.UserModel;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

    User getCurrentUser(String username);

    UserModel getCurrentUserModel(String username);

    UpdateUser updateCurrentUser(String username, UpdateUser updateUser);

    void setPassword(String username, NewPassword newPassword);

    void updateUserImage(String username, MultipartFile image);

    StoredImage getUserImage(Integer id);
}
