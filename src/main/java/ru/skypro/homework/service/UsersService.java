package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UsersService {

    boolean setPassword(NewPassword newPassword);

    User getUser();

    boolean updateUser(UpdateUser updateUser);

    boolean updateUserImage(MultipartFile file);

}
