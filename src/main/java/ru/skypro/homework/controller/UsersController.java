package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Register;

@RestController
@RequestMapping("/users")
public class UsersController {


    @PostMapping("/setPassword/")
    public void updatePassword() {

    }

    @GetMapping("/me/")
    public Register getInfoAboutUser() {
        return new Register(); //изменить на DTO
    }

    @PatchMapping("/me/")
    public Register updateInfoAboutUser() {
        return new Register(); //изменить на DTO
    }

    @PatchMapping("/me/image")
    public void updateUsersProfilePicture() {
    }
}
