package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

public interface UserService {
    ResponseEntity<?> setPassword(Authentication authentication, NewPassword newPassword);

    ResponseEntity<?> getProfile(Authentication authentication, User user);

    ResponseEntity<?> updateUser(Authentication authentication, UpdateUser updateUser);

    ResponseEntity<?> updateImage(Authentication authentication, MultipartFile photo);
}
