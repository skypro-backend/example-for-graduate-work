package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Register;

@RestController
@RequestMapping("/register")
public class RegistrationController {

    @PostMapping
    public ResponseEntity<Object> registerUser(@RequestBody Register registerData) {
        // Ваша логика регистрации пользователя на основе данных из Register объекта
        // Пример:
        // userService.registerUser(registerData);

        // Возвращаем статус 201 при успешной регистрации
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
