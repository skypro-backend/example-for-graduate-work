package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.request.LoginRequestDto;
import ru.skypro.homework.dto.request.RegisterRequestDto;
import ru.skypro.homework.service.AuthService;

/**
 * Контроллер аутентификации и регистрации пользователей.
 * Отвечает за:
 * - вход в систему,
 * - регистрацию новых пользователей.
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Аутентификация пользователя в системе.
     * @param login данные для входа (имя пользователя, пароль)
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto login) {
        authService.login(login.getUsername(), login.getPassword());
        return ResponseEntity.ok().build();
    }

    /**
     * Регистрация нового пользователя.
     * @param register данные пользователя
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequestDto register) {
        authService.register(register);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
