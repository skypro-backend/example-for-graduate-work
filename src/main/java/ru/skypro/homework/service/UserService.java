package ru.skypro.homework.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;

import java.io.IOException;

public interface UserService {
    void updatePassword(NewPassword newPassword, String username);
    User getInformation(String username);
    UpdateUser updateInformationAboutUser(UpdateUser updateUser, String username);
    void UpdateImage(MultipartFile file, String username) throws IOException;

//    добавлен метод для вывода изображениея пользователя
//    byte [] getImage(String username) throws IOException;

    ResponseEntity<Resource> getImage(String username) throws IOException;


}