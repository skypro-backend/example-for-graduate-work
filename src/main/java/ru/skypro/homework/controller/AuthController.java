package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;

import javax.validation.Valid;

/**
 * REST-контроллер регистрации и авторизации.
 */
@RestController
@Validated
public class AuthController {

    private final AuthService authService;

    public AuthController(
            AuthService authService
    ) {
        this.authService = authService;
    }

    /**
     * Регистрирует пользователя.
     *
     * @param register регистрационные данные
     * @return 201 при успешной регистрации;
     * 400, если username уже существует
     */
    @PostMapping("/register")
    public ResponseEntity<Void> register(
            @Valid @RequestBody Register register
    ) {
        boolean registered =
                authService.register(register);

        if (!registered) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .build();
    }

    /**
     * Проверяет логин и пароль пользователя.
     *
     * @param login данные для входа
     * @return 200 при успешном входе;
     * 401 при неверных данных
     */
    @PostMapping("/login")
    public ResponseEntity<Void> login(
            @Valid @RequestBody Login login
    ) {
        boolean authenticated =
                authService.login(login);

        if (!authenticated) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .build();
        }

        return ResponseEntity.ok().build();
    }
}
