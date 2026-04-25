package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "API для управления профилем пользователя")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "Обновление пароля", description = "Изменяет пароль текущего пользователя после проверки текущего пароля", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NewPassword.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Пароль успешно изменён"),
            @ApiResponse(responseCode = "401", description = "Текущий пароль неверен"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещён")
    })
    @PostMapping("/set_password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        try {
            userService.updatePassword(authentication.getName(), newPassword);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).build();
        }
    }

    @Operation(summary = "Получение информации об авторизованном пользователе", description = "Возвращает данные текущего аутентифицированного пользователя", responses = {
            @ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(Authentication authentication) {
        User user = userService.getUser(authentication.getName());
        return ResponseEntity.ok(user);
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе", description = "Изменяет имя, фамилию и телефон текущего пользователя", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateUser.class))), responses = {
            @ApiResponse(responseCode = "200", description = "Профиль успешно обновлён", content = @Content(schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
        User updatedUser = userService.updateUser(authentication.getName(), updateUser);
        return ResponseEntity.ok(updatedUser);
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя", description = "Заменяет изображение профиля пользователя", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Форма с файлом изображения", required = true, content = @Content(mediaType = MediaType.MULTIPART_FORM_DATA_VALUE, schema = @Schema(type = "object", description = "Объект, содержащий файл 'image'"))), responses = {
            @ApiResponse(responseCode = "200", description = "Аватар успешно обновлён"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserImage(@RequestPart("image") byte[] image, Authentication authentication) {
        userService.updateImage(authentication.getName(), image);
        return ResponseEntity.ok().build();
    }
}