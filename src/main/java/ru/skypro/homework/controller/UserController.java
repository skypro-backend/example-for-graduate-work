package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<?> newPassword(@RequestBody NewPassword newPassword) {
//запрос в сервис
        return ResponseEntity.ok().build();

    }
    @GetMapping("/me")
    public ResponseEntity<?> userDTO() {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/me/image")
    public ResponseEntity<?> image(@RequestBody String image) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
}
