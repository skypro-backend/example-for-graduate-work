package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Register;

@RestController
@RequestMapping("/users")
public class UsersController {


    @PostMapping("/setPassword")
    public void updatePassword() {

    }

    @GetMapping("/me")
    public ResponseEntity<?> getInfoAboutUser() {
        return new ResponseEntity<>(HttpStatus.OK);//изменить на DTO
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateInfoAboutUser() {
        return new ResponseEntity<>(HttpStatus.OK); //изменить на DTO
    }

    @PatchMapping("/me/image")
    public void updateUsersProfilePicture() {
    }
}
