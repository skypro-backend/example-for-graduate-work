package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.request.NewPasswordRequestDto;
import ru.skypro.homework.dto.request.UpdateUserRequestDto;
import ru.skypro.homework.dto.response.UserResponseDto;

public interface UserService {
    void setPassword(NewPasswordRequestDto dto);

    UserResponseDto getCurrentUser();

    UserResponseDto updateUser(UpdateUserRequestDto dto);

    void updateUserImage(MultipartFile image);
}
