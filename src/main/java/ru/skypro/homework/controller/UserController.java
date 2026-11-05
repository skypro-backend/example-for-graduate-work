package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.users.NewPasswordDto;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизированном пользователе")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUser(authentication.getName()));
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPass,
                                            Authentication authentication) {
        userService.setPassword(authentication.getName(), newPass);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    @Operation(summary = "обновление информации об авторизированном пользователе")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto dto,
                                                    Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(authentication.getName(), dto));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "обновление аватара авторизованного пользователя")
    public ResponseEntity<Void> updateUserImage(@RequestPart("image") MultipartFile image,
                                                Authentication authentication) {
        userService.updateUserImage(authentication.getName(), image);
        return ResponseEntity.ok().build();
    }
}
