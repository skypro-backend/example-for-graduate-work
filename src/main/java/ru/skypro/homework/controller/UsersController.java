package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    //!!!!Доработать внутренность
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(
            @RequestBody NewPassword newPassword)
    {
        return ResponseEntity.ok().build();
    }

    //!!!!Доработать внутренность
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(new User());
    }

    //!!!!Доработать внутренность
    @PostMapping("/me")
    public ResponseEntity<Void> updateUser(
            @RequestBody User user)
    {
        return ResponseEntity.ok().build();
    }

    //!!!!Доработать внутренность
    @PostMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestBody MultipartFile image){
        return ResponseEntity.ok().build();
    }

}
