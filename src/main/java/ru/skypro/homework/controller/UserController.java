package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.NewPasswordDto;
import ru.skypro.homework.dto.model_dto.UpdateUserDto;
import ru.skypro.homework.dto.model_dto.UserDto;

/**
 * Класс-контроллер для обработки запросов, связанных с пользователями.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Tag (name = "Пользователи", description = "работа с пользователями")
public class UserController {
    @Operation (
              summary = "Обновление пароля"
    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }
    @Operation (
              summary = "Получение информации об авторизованном пользователе"
    )
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok(new UserDto());
    }

    @Operation (
              summary = "Обновление информации об авторизованном пользователе"
    )
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> UpdateUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(updateUserDto);
    }
    @Operation (
              summary = "Обновление аватара авторизованного пользователя"
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UpdateUserDto> updateUserImage(@RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
