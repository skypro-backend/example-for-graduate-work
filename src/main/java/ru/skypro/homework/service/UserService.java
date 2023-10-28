package ru.skypro.homework.service;

import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.model.UserModel;
import ru.skypro.homework.projections.NewPassword;
import ru.skypro.homework.projections.Register;
import ru.skypro.homework.projections.UpdateUser;

public interface UserService {

    UserDTO getUser(Authentication authentication);

    void updatePassword(NewPassword newPassword);

    UpdateUser updateUser(UpdateUser updateUser, Authentication authentication);

    String update(String image);
}
