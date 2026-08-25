package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Управление профилями компаний и мастеров платформы Sopetov")
public class UsersController {

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(new User());
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации авторизованного пользователя")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(new UpdateUser());
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватарки пользователя/логотипа компании")
    public ResponseEntity<Void> updateUserImage(@RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}