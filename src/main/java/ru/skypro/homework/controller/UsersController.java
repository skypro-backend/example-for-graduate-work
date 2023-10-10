package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private UserService userService;

    //    Обновление пароля
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        if(userService.setPassword(newPassword)){
            return ResponseEntity.ok().build();
        }else if (newPassword.getCurrentPassword() == null){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    //    Получение информации об авторизованном пользователе
    @GetMapping("/me")
    public ResponseEntity<?> getProfile(@RequestBody User user) {
        if(userService.getProfile(user)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //    Обновление информации об авторизованном пользователе
    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser) {
        if(userService.updateUser(updateUser)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    //    Обновление аватара авторизованного пользователя
    @PatchMapping("/me/image")
    public ResponseEntity<?> updateImage(@RequestPart (value = "photo", required = false)MultipartFile photo) {
        if(userService.updateImage(photo)){
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }
}
