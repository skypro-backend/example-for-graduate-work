package ru.skypro.flea.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.flea.controller.UserApi;
import ru.skypro.flea.dto.NewPasswordDto;
import ru.skypro.flea.dto.UpdateUserDto;
import ru.skypro.flea.dto.UserDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@Tag(name = "Users")
public class UserApiController implements UserApi {

    @Override
    public ResponseEntity<Void> setPassword(NewPasswordDto newPassword) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<UpdateUserDto> updateUser(UpdateUserDto updateUser) {
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> updateUserImage(MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}
