package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPasswordDto;
import ru.skypro.homework.dto.user.UpdateUserDto;
import ru.skypro.homework.dto.user.UserDto;


@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok(new UserDto());
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(schema = @Schema(implementation = UpdateUserDto.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(updateUserDto);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}