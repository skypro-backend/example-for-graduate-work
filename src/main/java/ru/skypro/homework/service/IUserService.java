package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.user.NewPassword;
import ru.skypro.homework.user.UpdateUser;
import ru.skypro.homework.user.User;
import ru.skypro.homework.dto.Register;
import org.springframework.security.core.Authentication;

public interface IUserService {
    User getCurrentUser(Authentication auth);
    User updateProfile(UpdateUser data, Authentication auth);
    Boolean changePassword(NewPassword data, Authentication auth);
    Integer register(Register data);
    void uploadAvatar(MultipartFile image, Authentication auth);
}