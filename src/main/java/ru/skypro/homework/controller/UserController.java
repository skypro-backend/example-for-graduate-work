package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.AuthorizedUserInfoDto;
import ru.skypro.homework.dto.UpdateAuthorizedUserInfoDto;
import ru.skypro.homework.dto.UpdatePasswordDto;
import ru.skypro.homework.enums.Role;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    @Operation(summary = "Обновление пароля")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping("/set_password")
    public ResponseEntity<String> setPassword(@RequestBody UpdatePasswordDto updatePasswordDto) {
        // Реализация обновления пароля
        return ResponseEntity.ok("Password updated successfully");
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<AuthorizedUserInfoDto> getUser() {
        // Реализация получения информации об авторизованном пользователе
        return ResponseEntity.ok(new AuthorizedUserInfoDto(1L, "email", "firstName", "lastName", "phone", Role.USER, ""));
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<UpdateAuthorizedUserInfoDto> updateUser(@RequestBody UpdateAuthorizedUserInfoDto updateUser) {
        // Реализация обновления информации об авторизованном пользователе
        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<String> updateUserImage(@RequestParam("image") MultipartFile image) {
        // Реализация обновления аватара авторизованного пользователя
        return ResponseEntity.ok("User image updated successfully");
    }
}
