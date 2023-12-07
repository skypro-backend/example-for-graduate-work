package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody User user) {
        if (user.getPassword == null) {
            return ResponseEntity.notFound().build();
        }
        userService.add(user.setPassword);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@RequestBody User user) {
        User curantUser = userService.findById(id = this.id);
        if (curantUser == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(curantUser);
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateMyProfile(@RequestBody User user) {
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        user.setFirstName();
        user.setLastName();
        user.setPhone();
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> updateMyAvatar(@RequestBody User user) {
        if (user.getImage == null) {
            return ResponseEntity.notFound().build();
        }
        user.setImage();
        return ResponseEntity.ok().build();
    }
}
