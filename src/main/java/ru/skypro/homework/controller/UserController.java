package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.NewPasswordService;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController {
    private final NewPasswordService newPasswordService;
    private final UserService userService;
    @GetMapping("/users/me/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable String id) {
        return ResponseEntity.ok(userService.get(Integer.valueOf(id)));
    }
    @GetMapping("/users/me")
    public ResponseEntity<UserDto> get() {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(new UserDto());
    }

    @PatchMapping("/users/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new UserDto());
    }

    @PatchMapping("/users/me/image")
    public ResponseEntity<String> updateUserImage(@RequestBody MultipartFile file) {
        return ResponseEntity.ok().build();
    }
    @PutMapping("/users/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new NewPasswordDto());
    }
}
