package ru.skypro.homework.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.io.IOException;


public interface UserService {

    User getMeDTO();

    void updPass(NewPassword newPassword);

    UpdateUser updUsr(UpdateUser updateUser);

    void updPhoto(MultipartFile photo) throws IOException;
}
