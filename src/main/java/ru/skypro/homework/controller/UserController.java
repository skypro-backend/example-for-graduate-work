package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи", description = "API для управления профилем пользователя")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Получить профиль текущего пользователя")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        log.info("GET /users/me — пользователь: {}", authentication.getName());
        return ResponseEntity.ok(userService.getUserProfile(authentication.getName()));
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновить профиль текущего пользователя")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUserDto,
                                              Authentication authentication) {
        log.info("PATCH /users/me — пользователь: {}", authentication.getName());
        return ResponseEntity.ok(userService.updateUser(authentication.getName(), updateUserDto));
    }

    @PostMapping("/set_password")
    @Operation(summary = "Сменить пароль текущего пользователя")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto,
                                            Authentication authentication) {
        log.info("POST /users/set_password — пользователь: {}", authentication.getName());
        userService.changePassword(authentication.getName(), newPasswordDto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновить аватар пользователя")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<Void> updateUserImage(@RequestPart("image") MultipartFile image,
                                                Authentication authentication) {
        log.info("PATCH /users/me/image — файл: {}", image.getOriginalFilename());
        return ResponseEntity.ok().build();
    }
}