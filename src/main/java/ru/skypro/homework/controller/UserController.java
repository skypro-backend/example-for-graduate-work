package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

/**
 * Контроллер для обработки HTTP-запросов, связанных с управлением профилем пользователя.
 * Предоставляет API-методы для получения, обновления информации, изменения пароля и аватара авторизованного пользователя.
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "Пользователи", description = "Управление профилем пользователя")
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля", operationId = "setPassword")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    public ResponseEntity<Void> changePassword(
            @Valid @RequestBody NewPassword request,
            Authentication authentication) {

        String email = authentication.getName();
        userService.changePassword(request, email);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе", operationId = "getUser")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {

        String email = authentication.getName();
        User user = userService.getCurrentUser(email);

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе", operationId = "updateUser")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = UpdateUser.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<UpdateUser> updateUserInfo(
            @Valid @RequestBody UpdateUser updateData,
            Authentication authentication) {

        String email = authentication.getName();
        UpdateUser updatedUser = userService.updateUser(updateData, email);

        return ResponseEntity.ok(updatedUser);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя", operationId = "updateUserImage")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Void> updateUserImage(
            @Parameter(description = "Файл изображения")
            @RequestParam("image") MultipartFile file,
            Authentication authentication) {

        String email = authentication.getName();
        userService.updateUserImage(file, email);

        log.info("Аватар обновлен для пользователя: {}", email);

        return ResponseEntity.ok().build();
    }

    /**
     * Вспомогательный класс для запроса регистрации
     */

    @lombok.Data
    public static class RegisterUserRequest {
        @Valid
        private User user;

        @javax.validation.constraints.NotBlank(message = "Пароль обязателен")
        @javax.validation.constraints.Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
        private String password;
    }
}