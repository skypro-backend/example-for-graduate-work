package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.User;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController {

    @GetMapping("/users/me")
    public ResponseEntity<User> get() {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(new User());
    }

    @PatchMapping("/users/me")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new User());
    }

    @PatchMapping("/users/me/image")
    public ResponseEntity<String> updateUserImage(@RequestBody MultipartFile file) {
        return ResponseEntity.ok().build();
    }
    @PutMapping("/users/set_password")
    public ResponseEntity<User> updatePassword(@RequestBody String password) {
        return ResponseEntity.ok().build();
    }
}
