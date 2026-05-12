package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.repository.UserRepository;

import java.util.Optional;

import javax.validation.Valid;

/**
 * Контроллер аутентификации и регистрации пользователей.
 * <p>
 * Предоставляет эндпоинты для:
 * <ul>
 *   <li>Входа в систему ({@code POST /login})</li>
 *   <li>Регистрации нового пользователя ({@code POST /register})</li>
 * </ul>
 * Все операции обрабатываются через {@link AuthService}, а данные пользователей
 * хранятся в {@link UserRepository}.
 * <p>
 * Использует Spring Security для управления аутентификацией:
 * после успешного входа создается объект {@link Authentication},
 * который сохраняется в контексте безопасности.
 *
 * @see AuthService — бизнес-логика аутентификации и регистрации
 * @see Login — DTO для входа (email + пароль)
 * @see Register — DTO для регистрации (имя, фамилия, телефон, email, пароль, роль)
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/")
@Tag(name = "Авторизация", description = "API для входа и регистрации пользователей")
public class AuthController {

    /**
     * Сервис аутентификации, отвечающий за логику входа и регистрации.
     */
    private final AuthService authService;

    /**
     * Репозиторий для доступа к данным пользователей.
     * Используется для получения сущности {@link UserEntity} после входа.
     */
    private final UserRepository userRepository;

    public AuthController(AuthService authService, UserRepository userRepository) {
        this.authService = authService;
        this.userRepository = userRepository;
    }

    /**
     * Выполняет вход пользователя в систему.
     * <p>
     * Проверяет учетные данные с помощью {@link AuthService#login(String, String)}.
     * При успешной аутентификации:
     * <ul>
     *   <li>Загружает пользователя из БД по email</li>
     *   <li>Создает объект {@link org.springframework.security.core.userdetails.User}</li>
     *   <li>Устанавливает аутентификацию в контексте Spring Security</li>
     * </ul>
     *
     * @param login DTO с полями {@code username} (email) и {@code password}
     * @return {@link ResponseEntity} со статусом 200 при успехе,
     * 401 — если логин или пароль неверны
     */
    @Operation(summary = "Авторизация пользователя", description = "Выполняет вход пользователя в систему по логину и паролю", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Login.class))), responses = {@ApiResponse(responseCode = "200", description = "Успешный вход"), @ApiResponse(responseCode = "401", description = "Неверные учётные данные")})
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody Login login) {
        if (authService.login(login.getUsername(), login.getPassword())) {
            UserEntity user = userRepository.findByEmail(login.getUsername()).orElseThrow();

            org.springframework.security.core.userdetails.User userDetails = new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getAuthorities());

            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, user.getPassword(), user.getAuthorities());

            SecurityContextHolder.getContext().setAuthentication(authentication);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /**
     * Регистрирует нового пользователя в системе.
     * <p>
     * Передает данные из DTO в {@link AuthService#register(Register)}.
     * Если логин (email) уже существует или данные некорректны — регистрация отклоняется.
     *
     * @param register DTO с данными нового пользователя: email, пароль, имя, фамилия, телефон, роль
     * @return {@link ResponseEntity} со статусом 201 при успешной регистрации,
     * 400 — если логин занят или данные не прошли валидацию
     */
    @Operation(summary = "Регистрация пользователя", description = "Создаёт нового пользователя с уникальным логином", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = "application/json", schema = @Schema(implementation = Register.class))), responses = {@ApiResponse(responseCode = "201", description = "Пользователь успешно зарегистрирован"), @ApiResponse(responseCode = "400", description = "Логин уже занят или данные некорректны")})
    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody Register register) {
        if (authService.register(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}