package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UsersService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword dto) {
        log.info("Запрос на смену пароля для пользователя");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизированном пользователе")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<User> getUser() {
        log.info("Запрос данных текущего пользователя (заглушка)");
        return ResponseEntity.ok(new User());
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизированном пользователе")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser dto) {
        log.info("Обновление профиля пользователя. Данные: {}", dto);
        // Возвращаем тот же DTO, который прислали (или пустой, если надо)
        return ResponseEntity.ok(dto);
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизированного пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    public ResponseEntity<?> updateUserImage(@RequestPart("image") MultipartFile image) {
        log.info("Загрузка аватара. Файл: {}, размер: {} байт", image.getOriginalFilename(), image.getSize());
        return ResponseEntity.ok().build();
    }
}
