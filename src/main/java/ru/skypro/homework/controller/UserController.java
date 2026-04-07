package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.UserService;
import lombok.Data;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
@Tag(name = "Пользователи", description = "Управление профилем пользователя")
public class UserController {
    private final UserService userService;

    /**
     * POST /users/set_password - Обновление пароля
     */
    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля", operationId = "setPassword")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    public ResponseEntity<Void> changePassword(@Valid @RequestBody NewPassword request,
                                               Authentication authentication
    ) {

        String email = authentication.getName(); // 👈 текущий пользователь

        userService.changePassword(request, email);

        return ResponseEntity.ok().build();

    }

    /**
     * GET /users/me - Получение информации об авторизованном пользователе
     */
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе", operationId = "getUser")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<User> getCurrentUser(Authentication authentication) {

        String email = authentication.getName(); // 👈 текущий пользователь

        User user = userService.getCurrentUser(email);

        return ResponseEntity.ok(user);

    }

    /**
     * PATCH /users/me - Обновление информации об авторизованном пользователе
     */
    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе", operationId = "updateUser")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = UpdateUser.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<UpdateUser> updateUserInfo(@Valid @RequestBody UpdateUser updateData,Authentication
                                                     authentication) {


        String email = authentication.getName();

        UpdateUser updatedUser = userService.updateUser(updateData, email);

        return ResponseEntity.ok(updatedUser);
    }

    /**
     * PATCH /users/me/image - Обновление аватара авторизованного пользователя
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя", operationId = "updateUserImage")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Файл изображения",
            required = true,
            content = @Content(
                    mediaType = MediaType.MULTIPART_FORM_DATA_VALUE,
                    schema = @Schema(implementation = UpdateUserImageRequest.class)
            )
    )
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Void> updateUserImage(
            @Parameter(description = "Файл изображения") @RequestParam("image") MultipartFile file,
            Authentication authentication) {



        String email = authentication.getName();

        userService.updateUserImage(file, email);

        return ResponseEntity.ok().build();

    }

    /**
     * POST /users/register - Регистрация нового пользователя
     */
    @PostMapping("/register")
    @Operation(summary = "Регистрация нового пользователя", operationId = "registerUser")
    @io.swagger.v3.oas.annotations.parameters.RequestBody(
            description = "Данные нового пользователя с паролем",
            required = true,
            content = @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = RegisterUserRequest.class)
            )
    )
    @ApiResponse(responseCode = "201", description = "Пользователь зарегистрирован")
    public ResponseEntity<Void> registerUser(@Valid @RequestBody RegisterUserRequest request) {
        userService.registerUser(request.getUser(), request.getPassword());
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Вспомогательный класс для запроса регистрации
    @Data
    public static class RegisterUserRequest {
        @Valid
        private User user;

        @javax.validation.constraints.NotBlank(message = "Пароль обязателен")
        @javax.validation.constraints.Size(min = 6, message = "Пароль должен содержать минимум 6 символов")
        private String password;

    }
}