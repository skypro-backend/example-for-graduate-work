package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UsersService;

import javax.validation.Valid;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Validated
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "Получение и обновление информации о пользователе, включая обновление пароля")
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля", description = "Смена пароля. " +
            "Необходимо ввести старый пароль и новый пароль.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пароль обновлен"),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация"),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен")
    })
    public ResponseEntity<Void> setPassword(@Valid @RequestBody NewPassword newPassword) {
        usersService.setPassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация получена", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = User.class))}),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация")
    })
    public ResponseEntity<User> getUser() {
        User user = usersService.getUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе",
            description = "Необходимо ввести имя, фамилию и номер телефона.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Информация обновлена", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = UpdateUser.class))}),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация")
    })
    public ResponseEntity<UpdateUser> patchUser(@Valid @RequestBody UpdateUser updateUser) {
        UpdateUser updatedUser = usersService.updateUser(updateUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя",
            description = "Загружает изображение для авторизованного пользователя в формате JPEG." +
                    "Сохраняет изображения в базе данных.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аватар обновлен"),
            @ApiResponse(responseCode = "401", description = "Требуется авторизация")
    })
    public ResponseEntity<Void> patchUserAvatar(@RequestParam("image") MultipartFile file) {
        usersService.updateUserImage(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
