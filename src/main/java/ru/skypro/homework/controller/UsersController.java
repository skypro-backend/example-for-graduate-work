package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Управление профилями компаний и мастеров платформы Sopetov")
public class UsersController {

    private final UserService userService;

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        userService.setPassword(newPassword, authentication.getName());
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    public ResponseEntity<User> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUser(authentication.getName()));
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации авторизованного пользователя")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(updateUser, authentication.getName()));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватарки пользователя/логотипа компании")
    public ResponseEntity<Void> updateUserImage(@RequestParam MultipartFile image, Authentication authentication) {
        userService.updateUserImage(image, authentication.getName());
        return ResponseEntity.ok().build();
    }
}