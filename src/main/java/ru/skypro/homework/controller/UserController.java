package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.util.CustomUserDetailsService;

import javax.validation.Valid;
import java.io.IOException;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    private final UserService userService;


    @Operation(summary = "Обновление пароля", description = "Метод ничего не возвращает. Принимает пароль в виде DTO и обновляет его у пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пароль обновлен"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "400", description = "Пользователь отправил пароли в неверном формате"),
            @ApiResponse(responseCode = "403", description = "Доступ для пользователя запрещен"),
    })
    @PostMapping("/set_password")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid NewPassword newPassword) {
        logger.info("Request change password: {}, new password: {}", newPassword.getCurrentPassword(), newPassword.getNewPassword());
        userService.updPass(newPassword);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение экземпляра пользователя", description = "Вовзращает DTO интерпретацию текущего пользователя")
    @ApiResponses(value = {


            @ApiResponse(responseCode = "200", description = "Ответ получен",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован",
                    content = @Content(mediaType = MediaType.ALL_VALUE)),
    })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<User> getMe() {
        return ResponseEntity.ok(userService.getMeDTO());
    }

    @Operation(summary = "Обновление данных пользователя", description = "Возвращает DTO c обновленными данными и обновляет их у пользователя.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Данные обновлены. Возвращен обьект с новыми данными.",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UpdateUser.class))),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "400", description = "Пользователь отправил пароли в неверном формате"),
    })
    @PatchMapping("/me")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updateUserInfo(@RequestBody @Valid UpdateUser updateUser) {
        userService.updUsr(updateUser);
        return ResponseEntity.ok(updateUser);
    }

    @Operation(summary = "Изменение аватара пользователя", description = "Ничего не возвращает. Получает мультипарт файл с изображением и обновляет его у пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение загружено."),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
    })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<?> updatePhoto(MultipartFile image) throws IOException {
        userService.updPhoto(image);
        return ResponseEntity.ok().build();
    }

}

