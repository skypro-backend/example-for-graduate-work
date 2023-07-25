package ru.skypro.flea.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.flea.controller.UserApi;
import ru.skypro.flea.dto.NewPassword;
import ru.skypro.flea.dto.UpdateUser;
import ru.skypro.flea.model.User;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Tag(name = "Пользователи")
public class UserApiController implements UserApi {

    @Override
    public ResponseEntity<Void> setPassword(NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UpdateUser> updateUser(UpdateUser updateUser) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateUserImage(MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}
