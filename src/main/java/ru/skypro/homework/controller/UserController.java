package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@RestController
@Tag(name = "Пользователи")
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity setPassword(@RequestBody NewPassword newPassword){
        return ResponseEntity.ok(null);
    }
    @GetMapping("/me")
    public ResponseEntity<User> getUser(){
        return null;
    }
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser){
        return null;
    }
    @PatchMapping("/me/image")
    public ResponseEntity updateUserImage(){
        return null;
    }
}
