package com.skypro.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.skypro.dto.LoginReq;
import com.skypro.dto.RegisterReq;
import com.skypro.service.AuthService;

/**
 * REST-контроллер для аутентификации и регистрации пользователей.
 * <p>
 * Предоставляет эндпоинты для входа в систему и создания новых учётных записей.
 * </p>
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    /**
     * Выполняет вход пользователя в систему.
     *
     * @param loginReq DTO с логином и паролем
     * @return статус 200 OK, если аутентификация прошла успешно,
     *         иначе статус 401 Unauthorized
     */
    @Operation(summary = "Авторизация пользователя", operationId = "login")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginReq loginReq) {
        if (authService.login(loginReq.getUsername(), loginReq.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Регистрирует нового пользователя в системе.
     *
     * @param registerReq DTO с данными для регистрации
     * @return статус 201 Created, если регистрация прошла успешно,
     *         иначе статус 400 Bad Request (если пользователь уже существует)
     */
    @Operation(summary = "Регистрация пользователя", operationId = "register")
    @ApiResponse(responseCode = "201", description = "Created")
    @ApiResponse(responseCode = "400", description = "Bad Request")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterReq registerReq) {
        if (authService.register(registerReq)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}