package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.ok(
                userService.getUser()
        );
    }

    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<User> updateUser(
            @Valid @RequestBody UpdateUser updateUser) {
        return ResponseEntity.ok(
                userService.updateUser(updateUser)
        );
    }

    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PatchMapping(
            value = "/me/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> updateUserImage(
            @RequestPart("image") MultipartFile image) {
        userService.updateImage(image);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Обновление пароля пользователя",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "400", description = "Bad Request"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")
            }
    )
    @PatchMapping("/me/password")
    public ResponseEntity<Void> updatePassword(
            @Valid @RequestBody NewPassword newPassword
    ) {

        userService.updatePassword(newPassword);

        return ResponseEntity.ok().build();
    }
}