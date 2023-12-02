package ru.skypro.homework.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    AuthServiceImpl authService;
    @PostMapping("/set_password")
    public void setPassword(Authentication authentication, @RequestBody NewPasswordDTO newPassword) {
         authService.setPassword(authentication.getName(), newPassword);
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
