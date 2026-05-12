package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Контроллер для управления профилем пользователя.
 * <p>
 * Обеспечивает следующие функции:
 * <ul>
 *   <li>Получение данных текущего пользователя ({@code GET /users/me})</li>
 *   <li>Обновление имени, фамилии и телефона ({@code PATCH /users/me})</li>
 *   <li>Смена пароля ({@code POST /users/set_password})</li>
 *   <li>Загрузка нового аватара ({@code PATCH /users/me/image})</li>
 * </ul>
 * Все операции требуют аутентификации — доступны только авторизованным пользователям.
 * <p>
 * Использует {@link UserService} для выполнения бизнес-логики,
 * включая проверку прав, обновление данных и работу с изображениями.
 *
 * @see User — DTO для передачи данных о пользователе
 * @see UpdateUser — DTO для обновления профиля
 * @see NewPassword — DTO для смены пароля
 * @see UserService — основной сервис работы с пользователями
 */
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "API для управления профилем пользователя")
public class UserController {

    /**
     * Сервис для выполнения операций с пользователями.
     */
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Изменение пароля текущего пользователя.
     * <p>
     * Требует указания текущего пароля и нового. Проверяется корректность текущего пароля.
     * Если неверен — возвращается статус 401.
     *
     * @param newPassword    DTO с полями {@code currentPassword} и {@code newPassword}
     * @param authentication текущий аутентифицированный пользователь
     * @return {@link ResponseEntity} со статусом 200 при успехе,
     * 401 — если текущий пароль неверен
     */
    @Operation(summary = "Обновление пароля", description = "Изменяет пароль текущего пользователя после проверки текущего пароля", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NewPassword.class))), responses = {@ApiResponse(responseCode = "200", description = "Пароль успешно изменён"), @ApiResponse(responseCode = "401", description = "Текущий пароль неверен"), @ApiResponse(responseCode = "400", description = "Некорректные данные запроса")})
    @PostMapping("/set_password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> setPassword(@RequestBody @Valid NewPassword newPassword, Authentication authentication) {
        try {
            userService.updatePassword(authentication.getName(), newPassword);
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(401).build();
        }
    }

    /**
     * Получение информации о текущем пользователе.
     * <p>
     * Возвращает полные данные: email, имя, фамилия, телефон, роль, ссылку на аватар.
     * Используется фронтендом для заполнения формы профиля.
     *
     * @param authentication текущий пользователь
     * @return {@link ResponseEntity} с объектом {@link User}
     */
    @Operation(summary = "Получение информации об авторизованном пользователе", description = "Возвращает данные текущего аутентифицированного пользователя", responses = {@ApiResponse(responseCode = "200", description = "Успешный ответ", content = @Content(schema = @Schema(implementation = User.class))), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")})
    @GetMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> getUser(Authentication authentication) {
        System.out.println("🔹 Authentication: " + authentication);
        System.out.println("🔹 Is Authenticated: " + authentication.isAuthenticated());
        System.out.println("🔹 Principal: " + authentication.getPrincipal());
        System.out.println("🔹 Authorities: " + authentication.getAuthorities());
        User user = userService.getUser(authentication.getName());
        return ResponseEntity.ok(user);
    }

    /**
     * Частичное обновление профиля пользователя.
     * <p>
     * Позволяет изменить имя, фамилию и телефон. Все поля проходят валидацию.
     * Доступно только авторизованным пользователям.
     *
     * @param updateUser     DTO с новыми данными
     * @param authentication текущий пользователь
     * @return {@link ResponseEntity} с обновлённым объектом {@link User}
     */
    @Operation(summary = "Обновление информации об авторизованном пользователе", description = "Изменяет имя, фамилию и телефон текущего пользователя", requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateUser.class))), responses = {@ApiResponse(responseCode = "200", description = "Профиль успешно обновлён", content = @Content(schema = @Schema(implementation = User.class))), @ApiResponse(responseCode = "400", description = "Некорректные данные запроса (например, неверный формат телефона)"), @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"), @ApiResponse(responseCode = "500", description = "Внутренняя ошибка сервера")})
    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<User> updateUser(@RequestBody @Valid UpdateUser updateUser, Authentication authentication) {
        User updatedUser = userService.updateUser(authentication.getName(), updateUser);
        return ResponseEntity.ok(updatedUser);
    }

    /**
     * Обновление аватара текущего пользователя.
     * <p>
     * Принимает изображение в формате JPEG/PNG через {@code multipart/form-data}.
     * Сохраняется как {@code user_{id}.jpg} в директории изображений.
     *
     * @param image          новый файл аватара
     * @param authentication текущий пользователь
     * @return {@link ResponseEntity} со статусом 200 при успехе,
     * 400 — если файл повреждён или пустой
     * @throws IOException если произошла ошибка чтения файла
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserImage(@RequestPart("image") MultipartFile image, Authentication authentication) throws IOException {
        userService.updateUserImage(authentication.getName(), image.getBytes());
        return ResponseEntity.ok().build();
    }
}