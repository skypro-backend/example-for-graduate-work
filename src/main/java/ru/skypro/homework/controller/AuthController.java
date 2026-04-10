package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;
/**
 * Контроллер для аутентификации пользователей.
 * Отвечает за регистрацию и проверку учетных данных.
 */
@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Регистрация нового пользователя.
     *
     * @param request данные для регистрации (username и password)
     */
    @PostMapping("/register")
    public void register(@RequestBody Register request) {
        authService.register(request);
    }

    /**
     * Регистрация нового пользователя.
     *
     * @param request данные для регистрации (username и password)
     */
    @PostMapping("/login")
    public void login(@RequestBody Login request) {
        authService.login(request.getUsername(), request.getPassword());
    }
}
