package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            description = "Возвращает полную информацию о пользователе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(new User());
    }


    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            description = "Обновляет информацию об авторизованном пользователе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(new UpdateUser());

    }


    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            description = "Обновляет аватар авторизованного пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @Operation(
            summary = "Обновление пароля",
            description = "Обновляет пароль пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
