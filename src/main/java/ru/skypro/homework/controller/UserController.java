package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import javax.validation.Valid;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {


    @Operation(summary = "Обновление пароля", description = "Метод ничего не возвращает. Принимает пароль в виде DTO и обновляет его у пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Пароль обновлен"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
            @ApiResponse(responseCode = "400", description = "Пользователь отправил пароли в неверном формате"),
            @ApiResponse(responseCode = "403", description = "Доступ для пользователя запрещен"),
    })
    @PostMapping("/set_password")
    public ResponseEntity<?> updatePassword(@RequestBody @Valid NewPassword newPassword) {
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
    @GetMapping("/me")

    public ResponseEntity<User> getMe() {
        return ResponseEntity.ok().build(); //Return USER here
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
    public ResponseEntity<?> updateUserInfo(@RequestBody @Valid UpdateUser updateUser) {
        return ResponseEntity.ok(updateUser);
    }

    @Operation(summary = "Изменение аватара пользователя", description = "Ничего не возвращает. Получает мультипарт файл с изображением и обновляет его у пользователя")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Изображение загружено."),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован"),
    })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePhoto(MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}

