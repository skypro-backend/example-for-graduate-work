package ru.skypro.homework.service.user;


import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.projection.NewPassword;
import ru.skypro.homework.projection.UpdateUser;
import ru.skypro.homework.projection.UserView;

import java.io.IOException;


public interface UserService {
    void setPassword(NewPassword newPassword, Authentication authentication);
    UserDTO getCurrentUser(Authentication authentication);
    public UserView getUserView(Authentication authentication);
    UpdateUser updateUser(UpdateUser updateUser,Authentication authentication);
    void updateImage(MultipartFile image, Authentication authentication) throws IOException;

}
