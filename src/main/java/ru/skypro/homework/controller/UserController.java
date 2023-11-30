package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.AdsService;
import ru.skypro.homework.service.CommentService;
import ru.skypro.homework.service.UserService;

/**
 * Класс-контроллер для обработки запросов о пользователе
 * @see UserService
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    /**
     * Метод для смены пароля
     * @param newPassword
     */
    @PostMapping("/set_password")
    @Operation(summary = "Сменить пароль пользователя")
    @ApiResponse(responseCode = "200",description = "OK")
    @ApiResponse(responseCode = "401",description = "Unauthorized")
    @ApiResponse(responseCode = "403",description = "Forbidden")
    void setPassword(@RequestBody NewPasswordDTO newPassword) {

        userService.setPassword(newPassword);
    }

    /**
     * Метод возвращающает информацию о пользователе
     * @return {@link UserDTO}
     */
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200",description = "OK")
    @ApiResponse(responseCode = "401",description = "Unauthorized")
    public UserDTO getUserInfo() {

        return userService.getUserInfo();
    }

    /**
     * Метод обновляет информация о пользователе
     * @param user
     * @return {@link UpdateUserDTO}
     */
    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponse(responseCode = "200",description = "OK")
    @ApiResponse(responseCode = "401",description = "Unauthorized")
    public UpdateUserDTO updateUserInfo(UpdateUserDTO user) {

        return userService.updateUser(user);
    }

    /**
     * Метод обновляет изображение пользователя
     * @param image
     */
    @PatchMapping("/me/image")
    @Operation(summary = "Обновление аватара авторизованного пользователе")
    @ApiResponse(responseCode = "200",description = "OK")
    @ApiResponse(responseCode = "401",description = "Unauthorized")
    void updateUserImage(MultipartFile image) {

        userService.updateUserImage(image);
    }

    /**
     * Метод возвращает изображение пользователя
     * @param id
     * @return byte[]
     */
    @GetMapping("/image/{id}")
    @Operation(summary = "Получение аватара пользователя")
    @ApiResponse(responseCode = "200",description = "OK")
    public byte[] getImage(@PathVariable String id) {

        return userService.getImage(id);
    }
}
