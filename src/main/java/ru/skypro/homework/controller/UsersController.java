package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

import java.awt.*;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("users")
public class UsersController {
    @PostMapping("/set_password")
    public void setPassword( @RequestBody NewPassword newPassword ) {
        //200, 401, 403, 404
    }

    @GetMapping("/me")
    public User getUser() {
        //200, 401, 403, 404
        return null;
    }

    @PatchMapping("/me")
    public User updateUser( @RequestBody User user ) {
        //200, 204, 401,403,404
        return null;
    }

    @PatchMapping("/me/image")
    public void updateUserImage( @RequestBody Image image ) {
        //200, 404
    }
}
