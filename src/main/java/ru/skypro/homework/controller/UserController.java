package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.*;

import ru.skypro.homework.service.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        if (isValidPassword(newPassword)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        User user = userService.getLoggedInUser();
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        User updatedUser = userService.updateUserDetails(updateUser);
        if (updatedUser != null) {
            return new ResponseEntity<>(updateUser, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image) {
        if (!image.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
    private boolean isValidPassword(NewPassword newPassword) {
        return newPassword.getCurrentPassword().length() >= 8
               && newPassword.getNewPassword().length() >= 8; // Проверка длины
    }
}
