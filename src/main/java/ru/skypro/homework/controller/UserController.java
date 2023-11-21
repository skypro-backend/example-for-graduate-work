package ru.skypro.homework.controller;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping ("/set_password")
    public ResponseEntity setPassword(@Valid @RequestParam NewPasswordDTO newPassword) {
        return ResponseEntity.ok(newPassword);
    }
    @GetMapping("/me")
    public ResponseEntity getUser() {
        UserDTO user = new UserDTO();
        return ResponseEntity.ok(user);
    }


}



/*        POST
        /users/set_password
        Обновление пароля
        Jump to definition

        GET
        /users/me
        Получение информации об авторизованном пользователе
        Jump to definition

        PATCH
        /users/me
        Обновление информации об авторизованном пользователе
        Jump to definition

        PATCH
        /users/me/image
        Обновление аватара авторизованного пользователя*/
