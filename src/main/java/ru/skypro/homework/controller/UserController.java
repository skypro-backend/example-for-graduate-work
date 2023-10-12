package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.projection.NewPassword;
import ru.skypro.homework.projection.UpdateUser;
import ru.skypro.homework.projection.UserView;
import ru.skypro.homework.service.user.UserService;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;

    /**
     * Этот метод обрабатывает POST-запросы на обновление пароля
     *
     * @param newPassword новый пароль
     * @param authentication данные авторизированного пользователя
     */
    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden")})
    public void setPassword(@RequestBody @Valid NewPassword newPassword,
                            Authentication authentication) {
        userService.setPassword(newPassword,authentication);
    }

    /**
     * Этот метод обрабатывает GET-запросы на получение информации об авторизированном пользователе
     *
     * @param authentication данные авторизированного пользователя
     * @return Возвращает информацию об авторизированном пользователе
     */
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",content = {
                    @Content (mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserView.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)})
    public ResponseEntity<UserView> getCurrentUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserView(authentication));
    }

    /**
     * Этот метод обрабатывает PATCH-запросы на обновление информации об авторизованном пользователе
     *
     * @param updateUser новая информация о пользователе
     * @param authentication данные авторизированного пользователя
     * @return Возвращает обновленную информацию о пользователе
     */
    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK",content = {
                    @Content (mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdateUser.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)})
    public ResponseEntity<UpdateUser> updateUser(@RequestBody @Valid UpdateUser updateUser,Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(updateUser,authentication));
    }

    /**
     * Этот метод обрабатывает PATCH-запросы на обновление аватара авторизованного пользователя
     *
     * @param image картинка
     * @param authentication данные авторизированного пользователя
     * @throws IOException если введен неверный формат данных
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})

    public void updateImage(@RequestParam MultipartFile image,
                            Authentication authentication) throws IOException {
        userService.updateImage(image, authentication);
    }
}
