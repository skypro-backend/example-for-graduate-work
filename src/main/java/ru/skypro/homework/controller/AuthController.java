package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.security.UserDetailServiceImpl;
import ru.skypro.homework.service.AuthService;

/**
 * AuthController - авторизация пользователя
 * <br>
 * <br>- login <i>(вход авторизованного пользователя)</i>;
 * <br>- register <i>(регистрация нового пользователя)</i>;
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final UserDetailServiceImpl userDetailService;

    @Operation(
            tags = "Авторизация",
            summary = "Авторизация пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = {@Content()}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
            }
    )
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if (userDetailService.login(login.getUsername(), login.getPassword())) {

            log.info("Пользователь " + login.getUsername() + ": Вход выполнен успешно!");

            return ResponseEntity.ok().build();
        } else {

            log.info("Вход не выполнен! " + login.getUsername() + " не авторизован!");

            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Operation(
            tags = "Регистрация",
            summary = "Регистрация пользователя",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = {@Content()}),
                    @ApiResponse(responseCode = "400", description = "Bad Request", content = {@Content()})
            }
    )
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        log.info("Поступил запрос на РЕГИСТРАЦИЮ...");
        if (userDetailService.register(register)) {

            log.info("Регистрация прошла успешно!" + register.getUsername() + " зарегистрирован!");

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {

            log.info("Регистрация не пройдена!");

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
