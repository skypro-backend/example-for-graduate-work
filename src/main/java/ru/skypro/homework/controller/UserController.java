package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи", description = "API для управления профилем пользователя")
public class UserController {

    @Operation(summary = "Получить профиль текущего пользователя")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        log.info("Вызван метод getUser");
        return ResponseEntity.ok(new UserDto());
    }

    @Operation(summary = "Обновить профиль текущего пользователя")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(schema = @Schema(implementation = UserDto.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        log.info("Вызван метод updateUser");
        return ResponseEntity.ok(new UserDto());
    }

    @Operation(summary = "Сменить пароль текущего пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        log.info("Вызван метод setPassword");
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Обновить аватар пользователя")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestPart("image") MultipartFile image) {
        log.info("Вызван метод updateUserImage с файлом: {}", image.getOriginalFilename());
        return ResponseEntity.ok().build();
    }
}
