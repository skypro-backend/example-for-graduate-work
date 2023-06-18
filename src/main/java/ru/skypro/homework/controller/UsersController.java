package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersController {

    @PostMapping("set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<User> getUser() {//User
        return ResponseEntity.ok().build();
    }

    @PatchMapping("me")
    public ResponseEntity<User> updateUser(User user) { //User
        return ResponseEntity.ok().build();
    }

    @PatchMapping("me/image")
    public ResponseEntity<?> updateUserImage(@RequestBody MultipartFile file) {
        return ResponseEntity.ok().build();
    }
}
