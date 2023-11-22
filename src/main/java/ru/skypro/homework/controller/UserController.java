package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@CrossOrigin("*")
@RestController
@Tag(name = "\uD83D\uDE4B Пользователи")
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Получить информацию об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь найден",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователь не авторизован (unauthorized)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещен (forbidden)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден (not found)",
                            content = @Content()
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    @Operation(
            summary = "Обновить информацию об авторизованном пользователе",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пользователь обнавлен",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "204",
                            description = "Пустой запрос (no content)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователь не авторизован (unauthorized)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещен (forbidden)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден (not found)",
                            content = @Content()
                    )
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        if (null == updateUserDTO) {
            return ResponseEntity.noContent().build();
        }

        UpdateUserDTO editedUser = userService.updateUser(updateUserDTO);
        if (null == editedUser) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(editedUser);
    }

    @Operation(
            summary = "Обновление пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пароль обновлен",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователь не авторизован (unauthorized)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещен (forbidden)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден (not found)",
                            content = @Content()
                    )
            }
    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setUserPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        return ResponseEntity.ok(userService.setPassword(newPasswordDTO));
    }

    @Operation(
            summary = "Обновить аватар авторизованного пользователя",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Картинка загружена",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден (not found)",
                            content = @Content()
                    )
            }
    )
    @RequestMapping(
            method = RequestMethod.PATCH,
            value = "/me/image",
            consumes = { "multipart/form-data" }
    )
    public ResponseEntity<?> loadUserImage(@RequestBody MultipartFile image) {
        return ResponseEntity.ok(userService.updateUserImage(image));
    }

}