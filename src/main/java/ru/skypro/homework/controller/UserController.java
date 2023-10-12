package ru.skypro.homework.controller;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.adsDTO.AdsDTO;
import ru.skypro.homework.dto.usersDTO.*;
public class UserController {

    @Operation(summary = "Обновление пароля")
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDTO> updatePassword(@RequestBody NewPasswordDTO user) {
        NewPasswordDTO newPasswordDTO = new NewPasswordDTO();
        return ResponseEntity.ok().body(newPasswordDTO);
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(new UserDTO());
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO user) {
        UpdateUserDTO updateUserDTO = new UpdateUserDTO();
        return ResponseEntity.ok().body(updateUserDTO);
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserAvatar(@RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}