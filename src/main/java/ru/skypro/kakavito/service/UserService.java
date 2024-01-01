package ru.skypro.kakavito.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.UpdateUserDTO;
import ru.skypro.kakavito.dto.UserDto;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.model.User;

import java.io.IOException;

public interface UserService {

    User getAuthorizedUser();

    boolean setNewPassword(String email, String currentPassword, String newPassword);

    UpdateUserDTO updateMyProfile(UpdateUserDTO updateUserDTO);

    void updateMyImage(MultipartFile file) throws IOException, ImageSizeExceededException;

    UserDto findById(Long id);
}
