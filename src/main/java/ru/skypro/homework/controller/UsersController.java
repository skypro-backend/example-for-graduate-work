package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@RestController
@RequestMapping("/users")
public class UsersController {

    @PostMapping("/set_password")
    public ResponseEntity<Object> setPassword(@RequestBody NewPassword newPassword) {
        // Логика обновления пароля
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        // Логика получения информации об авторизованном пользователе
        return ResponseEntity.ok(new User());
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        // Логика обновления информации об авторизованном пользователе
        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Object> updateUserImage(@RequestParam("image") MultipartFile image) {
        // Логика обновления аватара авторизованного пользователя
        return ResponseEntity.ok().build();
    }
}