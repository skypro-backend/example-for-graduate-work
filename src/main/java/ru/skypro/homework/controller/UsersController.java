package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.impl.UserService;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<?> getUser () {
        User user = userService.getUser();
            return ResponseEntity.ok().body(user);
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUser updateUser) {
        userService.updateUser(updateUser);
        return ResponseEntity.ok().body(updateUser);
    }

    @PostMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    public ResponseEntity<?> updateUserImage(@RequestParam("image") MultipartFile file) {
        userService.updateUserImage(file);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponse(responseCode = "200",
            description = "Операция успешна")
    @ApiResponse(responseCode = "401",
            description = "Ошибка авторизации")
    @ApiResponse(responseCode = "403",
            description = "Операция запрещена")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {
        userService.updatePassword(newPassword);
        return ResponseEntity.ok().build();

    }
}