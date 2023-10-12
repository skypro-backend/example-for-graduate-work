package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/register")
    public ResponseEntity<RegisterDto> registerUser(@RequestBody RegisterDto registerDto) {
        return ResponseEntity.ok(new RegisterDto());
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDto> loginUser(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok(new LoginDto());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo() {
        return ResponseEntity.ok(new UserDto());
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUserInfo(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(new UpdateUserDto());
    }

    @PatchMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestPart MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
