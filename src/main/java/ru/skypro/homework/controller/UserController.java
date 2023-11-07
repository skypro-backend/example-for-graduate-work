package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.User;

@RestController
@RequestMapping("/users")
public class UserController {
    @PostMapping ("/set_password")
    public ResponseEntity setPassword(@RequestBody String password) {
        User user = new User();
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
