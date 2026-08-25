package ru.skypro.homework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

/**
 * Контроллер для управления данными пользователя.
 * Предоставляет REST API для получения, обновления информации пользователя,
 * смены пароля и управления аватаром.
 */
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Изменяет пароль авторизованного пользователя.
     * Требуется авторизация.
     *
     * @param newPassword DTO с текущим и новым паролем
     * @param authentication объект аутентификации
     * @return ResponseEntity с кодом 200 OK при успехе,
     *         или 400 Bad Request при неверном текущем пароле
     */
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword,
                                         Authentication authentication) {
        log.debug("Set password request for user: {}", authentication.getName());
        try {
            userService.setPassword(authentication.getName(), newPassword);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    /**
     * Получает информацию об авторизованном пользователе.
     * Требуется авторизация.
     *
     * @param authentication объект аутентификации
     * @return ResponseEntity с информацией о пользователе
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUser(Authentication authentication) {
        log.debug("Get user info for: {}", authentication.getName());
        User user = userService.getUser(authentication.getName());
        return ResponseEntity.ok(user);
    }

    /**
     * Обновляет информацию об авторизованном пользователе.
     * Требуется авторизация.
     *
     * @param updateUser обновленные данные
     * @param authentication объект аутентификации
     * @return ResponseEntity с обновленной информацией
     */
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser,
                                                 Authentication authentication) {
        log.debug("Update user info for: {}", authentication.getName());
        UpdateUser result = userService.updateUser(authentication.getName(), updateUser);
        return ResponseEntity.ok(result);
    }

    /**
     * Обновляет аватар авторизованного пользователя.
     * Требуется авторизация.
     *
     * @param image файл с изображением
     * @param authentication объект аутентификации
     * @return ResponseEntity с кодом 200 OK
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam("image") MultipartFile image,
                                             Authentication authentication) {
        log.debug("Update user image for: {}", authentication.getName());
        userService.updateUserImage(authentication.getName(), image);
        return ResponseEntity.ok().build();
    }
}