package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.users.*;

@RestController
@RequestMapping
public class UserController {

    @Operation(summary = "Обновление пароля", tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PostMapping("users/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение информации об авторизованном пользователе", tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @GetMapping("users/me")
    public UserDto getUser() {
        return new UserDto();
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе", tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = UpdateUserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PatchMapping("users/me")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return new UpdateUserDto();
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя", tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = @Content(schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = String.class)))
    })
    @PatchMapping("users/me/image")
    public ResponseEntity<?> updateUserImage(
            @Parameter(name = "image", required = true)
            @RequestParam String image) {
        return ResponseEntity.ok().build();
    }
}
