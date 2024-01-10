package ru.skypro.kakavito.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.UpdateUserDTO;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.model.User;

import java.io.IOException;
import java.util.Optional;

public interface UserService {

    User getAuthorizedUser();

    boolean setNewPassword(String email, String currentPassword, String newPassword);

    UpdateUserDTO updateMyProfile(UpdateUserDTO updateUserDTO);

    void updateMyImage(MultipartFile file, UserDetails userDetails) throws IOException, ImageSizeExceededException;

    Optional<User> findById(Integer id);
}
