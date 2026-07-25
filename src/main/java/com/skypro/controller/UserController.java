package com.skypro.controller;

import com.skypro.dto.NewPassword;
import com.skypro.dto.UpdateUser;
import com.skypro.dto.User;
import com.skypro.service.ImageService;
import com.skypro.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * REST-контроллер для управления данными пользователей.
 * <p>
 * Предоставляет эндпоинты для получения и обновления профиля,
 * смены пароля и загрузки аватарки.
 * </p>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    public UserController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    /**
     * Возвращает информацию о текущем авторизованном пользователе.
     *
     * @param authentication объект аутентификации текущего пользователя
     * @return DTO {@link User} с данными пользователя
     */
    @Operation(summary = "Получение информации об авторизованном пользователе", operationId = "getUser")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = User.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<User> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserByUsername(authentication.getName()));
    }

    /**
     * Обновляет данные текущего авторизованного пользователя.
     *
     * @param updateUser     DTO с новыми данными (имя, фамилия, телефон)
     * @param authentication объект аутентификации текущего пользователя
     * @return обновлённый DTO {@link User}
     */
    @Operation(summary = "Обновление информации об авторизованном пользователе", operationId = "updateUser")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UpdateUser.class)))
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping("/me")
    public ResponseEntity<User> updateUser(@RequestBody UpdateUser updateUser,
                                           Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(authentication.getName(), updateUser));
    }

    /**
     * Меняет пароль текущего пользователя.
     *
     * @param newPassword    DTO с текущим и новым паролем
     * @param authentication объект аутентификации текущего пользователя
     * @return статус 200 OK при успешной смене пароля
     */
    @Operation(summary = "Обновление пароля", operationId = "setPassword")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword,
                                         Authentication authentication) {
        userService.changePassword(authentication.getName(),
                newPassword.getCurrentPassword(), newPassword.getNewPassword());
        return ResponseEntity.ok().build();
    }

    /**
     * Загружает новую аватарку для текущего пользователя.
     *
     * @param image          файл изображения
     * @param authentication объект аутентификации текущего пользователя
     * @return статус 200 OK при успешной загрузке
     */
    @Operation(summary = "Обновление аватара авторизованного пользователя", operationId = "updateUserImage")
    @ApiResponse(responseCode = "200", description = "OK")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatar(@RequestParam("image") MultipartFile image,
                                          Authentication authentication) {
        String filename = imageService.saveAvatar(image);
        userService.updateAvatar(authentication.getName(), filename);
        return ResponseEntity.ok().build();
    }
}