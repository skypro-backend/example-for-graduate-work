package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;

@RestController
@Tag(name = "Авторизация")
public class AuthController {

    // Закомментировал зависимость от AuthService, чтобы не падало
    // private final AuthService authService;
    //
    // public AuthController(AuthService authService) {
    //     this.authService = authService;
    // }

    @PostMapping("/login")
    @Operation(summary = "Авторизация пользователя")
    public ResponseEntity<Void> login(@RequestBody Login login) {
        // Заглушка
        return ResponseEntity.ok().build();
    }

    @PostMapping("/register")
    @Operation(summary = "Регистрация пользователя")
    public ResponseEntity<Void> register(@RequestBody Register register) {
        // Заглушка
        return ResponseEntity.ok().build();
    }
}