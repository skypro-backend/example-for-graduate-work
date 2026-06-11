package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.constant.WebConfigConstant;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@Slf4j
@CrossOrigin(value = WebConfigConstant.CORS_ORIGIN)
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(new User());
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = UpdateUser.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        // Согласно критериям, PUT/PATCH возвращает обновленный DTO
        return ResponseEntity.ok(new UpdateUser());
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<Void> updateUserImage(@RequestParam MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}