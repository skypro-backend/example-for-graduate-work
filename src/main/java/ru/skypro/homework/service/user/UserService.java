package ru.skypro.homework.service.user;


import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.projection.NewPassword;
import ru.skypro.homework.projection.UpdateUser;


public interface UserService {
    ResponseEntity<?> setPassword(NewPassword newPassword);
    ResponseEntity<?> getCurrentUser();
    ResponseEntity<?> updateUser(UpdateUser updateUser);
    ResponseEntity<?> updateImage(MultipartFile image);

}
