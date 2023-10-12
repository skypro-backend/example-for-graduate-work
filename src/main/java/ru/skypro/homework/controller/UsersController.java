package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<?> setPassword(Authentication authentication, @RequestBody NewPassword newPassword) {
       return userService.setPassword(authentication, newPassword);
    }

    //    Получение информации об авторизованном пользователе
    @GetMapping("/me")
    public User getProfile(Authentication authentication) {
        return userService.getProfile(authentication);
    }

    //    Обновление информации об авторизованном пользователе
    @PatchMapping("/me")
    public UpdateUser updateUser(Authentication authentication,
                                        @RequestBody UpdateUser updateUser) {
        return userService.updateUser(authentication, updateUser);
    }

    //    Обновление аватара авторизованного пользователя
    @PatchMapping("/me/image")
    public ResponseEntity<?> updateImage(Authentication authentication,
                                         @RequestPart (value = "photo", required = false)MultipartFile photo) {
        return userService.updateImage(authentication, photo);
    }
}
