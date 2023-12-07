package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword() {
        return null;
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile() {
        return null;
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateMyProfile() {
        return null;
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> updateMyAvatar() {
        return null;
    }
}
