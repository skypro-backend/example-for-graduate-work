package ru.skypro.homework.service.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.projection.NewPassword;
import ru.skypro.homework.projection.UpdateUser;


public interface UserService {
    boolean setPassword(NewPassword newPassword);
    UserDTO getCurrentUser();
    UserDTO updateUser(UpdateUser updateUser);
    void updateImage(MultipartFile image);

}
