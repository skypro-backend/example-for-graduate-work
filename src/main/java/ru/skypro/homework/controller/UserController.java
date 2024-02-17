package ru.skypro.homework.controller;


import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.entities.UserEntity;
import ru.skypro.homework.dto.Password;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody Password password) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserEntity> getUser() {
        return ResponseEntity.ok().build();
    }


    @PatchMapping("/me")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        if (user != null) {
            userService.updateUserEntity(user);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile avatar) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test")
    public String test() {
        return "TEST";
    }
}