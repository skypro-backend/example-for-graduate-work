package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Контроллер для управления данными пользователя.
 * Обрабатывает запросы, связанные с профилем пользователя.
 * Основные функции:
 * - смена пароля;
 * - получение информации о текущем пользователе;
 * - обновление данных профиля;
 * - загрузка и обновление аватара пользователя.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;

    /**
     * Сменить пароль текущего пользователя.
     * Только для авторизованных пользователей.
     * @param newPassword    DTO с текущим и новым паролем
     * @param authentication данные авторизованного пользователя
     * @return 200 OK если пароль успешно изменён, 400 Bad Request если старый пароль неверный
     */
    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    public ResponseEntity<Void> setPassword(@Valid @RequestBody NewPassword newPassword,
                                            Authentication authentication) {
        String email = authentication.getName();

        boolean changed = userService.changePassword(
                email,
                newPassword.getCurrentPassword(),
                newPassword.getNewPassword()
        );

        if (changed) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    /**
     * Получить информацию о текущем авторизованном пользователе.
     * @param authentication данные авторизованного пользователя
     * @return DTO с данными пользователя
     */
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<User> getUser(Authentication authentication) {
        String email = authentication.getName();
        return userService.getUserByEmail(email)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * Обновить данные текущего пользователя (имя, фамилия, телефон).
     * @param updateUser     DTO с новыми данными
     * @param authentication данные авторизованного пользователя
     * @return обновлённые данные пользователя
     */
    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<UpdateUser> updateUser(@Valid @RequestBody UpdateUser updateUser,
                                                 Authentication authentication) {
        String email = authentication.getName();

        return userService.getUserByEmail(email)
                .map(userDto -> {
                    userDto.setFirstName(updateUser.getFirstName());
                    userDto.setLastName(updateUser.getLastName());
                    userDto.setPhone(updateUser.getPhone());
                    User updated = userService.updateUser(userDto)
                            .orElseThrow(() -> new RuntimeException("Failed to update user"));


                    UpdateUser response = new UpdateUser();
                    response.setFirstName(updated.getFirstName());
                    response.setLastName(updated.getLastName());
                    response.setPhone(updated.getPhone());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    /**
     * Обновить аватар текущего пользователя.
     * @param image          новый файл аватара
     * @param authentication данные авторизованного пользователя
     * @return 200 OK при успешной загрузке
     * @throws IOException если ошибка при сохранении файла
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image,
                                                Authentication authentication) throws IOException {
        String email = authentication.getName();
        UserEntity user = userService.getUserEntityByEmail(email);
        userService.updateUserAvatar(user.getId(), image);
        return ResponseEntity.ok().build();
    }
}