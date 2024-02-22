package ru.skypro.sitesforresaleofthings.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.sitesforresaleofthings.dto.NewPasswordDTO;
import ru.skypro.sitesforresaleofthings.dto.UpdateUserDTO;
import ru.skypro.sitesforresaleofthings.dto.UserDTO;

/**
 * Крнтроллер для работы с пользователями
 */
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    // здесь будут поля сервисов
    public UserController() {
    }

    // здесь будет конструктор с параметрами

    @PostMapping("/set_password")
    @Operation(
            summary = "Обновление пароля"
    )
    @ApiResponse(
            responseCode = "200",
            description = "ОК"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(
            summary = "Получение информации об авторизованном пользователе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    public ResponseEntity<UserDTO> getUser() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok(new UserDTO());
    }

    @PatchMapping("/me")
    @Operation(
            summary = "Обновление информации об авторизованном пользователе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    public ResponseEntity<UpdateUserDTO> updateUser() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok(new UpdateUserDTO());
    }

    @PatchMapping("/me/image")
    @Operation(
            summary = "Обновление аватара авторизованного пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    public ResponseEntity<?> updateUserImage() {
        // написать код + продумать возможные исключения
        return ResponseEntity.ok().build();
    }
}