package ru.skypro.homework.service;


import org.springframework.core.io.Resource;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {
    void register(Register register);

    boolean userExists(String email);

    void changePassword(Authentication authentication, NewPassword newPassword);

    User getUser(Authentication authentication);

    void updateUser(Authentication authentication, UpdateUser updateUser);

    void updateUserImage(Authentication authentication, MultipartFile image) ;

    Resource updateUserImageAndReturn(Authentication authentication, MultipartFile image);

}
