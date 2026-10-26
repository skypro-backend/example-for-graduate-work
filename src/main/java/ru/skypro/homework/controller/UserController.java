package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.NewPasswordDto;
import ru.skypro.homework.dto.users.Role;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизированном пользователе")
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok(new UserDto(1, "any_login@mail.ru", "Ivan",
                "Ivanov", "+79996661313", Role.USER, null));
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPass) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    @Operation(summary = "обновление информации об авторизированном пользователе")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto dto) {
        return ResponseEntity.ok(new UpdateUserDto("Ivan",
                "Ivanov", "+7(999)666-13-13"));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "обновление аватара авторизованного пользователя")
    public ResponseEntity<Void> updateUserImage(@RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
