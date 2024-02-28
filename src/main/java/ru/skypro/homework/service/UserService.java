package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.model.User;

public interface UserService {
    User updatePassword(NewPassword dto);

    User getMe();

    User updateMe(UpdateUser dto);

    User updateMyImage(MultipartFile imageFile);
}

