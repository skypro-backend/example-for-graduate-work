package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.*;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.service.UserService;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;


    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDto newPassword) {
        try {
            userService.updatePassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
            return ResponseEntity.ok().body("Password updated successfully");
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Error updating password: " + e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        UserDto user = userService.getUserDetails();
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUser) {
        try {
            UpdateUserDto updatedUser = userService.updateUser(updateUser);
            return ResponseEntity.ok(updatedUser);
        } catch (Exception e) {
            return ResponseEntity.status(401).build();
        }
    }

    @PatchMapping("/me/image")
    public ResponseEntity<String> updateUserImage(@RequestBody MultipartFile image) throws IOException, IOException {
        byte[] imageBytes = image.getBytes();
        userService.updateUserImage(imageBytes);
        return ResponseEntity.ok("User image updated successfully");
    }
}
