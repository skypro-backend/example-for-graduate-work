package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exceptions.ImageSizeExceededException;
import ru.skypro.homework.model.User;

import java.io.IOException;

public interface UserService {

    User getAuthorizedUser();

    boolean setNewPassword(String email, String currentPassword, String newPassword);

    UpdateUserDTO updateMyProfile(UpdateUserDTO updateUserDTO);

    void updateMyImage(MultipartFile file) throws IOException, ImageSizeExceededException;

    UserDto findById(Long id);
}
