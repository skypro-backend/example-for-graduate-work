package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.catalina.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;

@RestController
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "API для управления профилем пользователя")
public class UserController {

    // ==================== 1. СМЕНА ПАРОЛЯ ====================

    @PostMapping("/set_password")
    @Operation(
            summary = "Обновление пароля",
            description = "Позволяет авторизованному пользователю сменить пароль, " +
                    "предоставив текущий пароль и новый пароль"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Пароль успешно изменён"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. Пользователь не авторизован",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden. Указан неверный текущий пароль",
                    content = @Content
            )
    })
    public ResponseEntity<?> setPassword(
            @Parameter(
                    description = "Объект с текущим и новым паролем",
                    required = true,
                    schema = @Schema(implementation = NewPassword.class)
            )
            @RequestBody NewPassword password
    ) {
        // Скелет: заглушка
        return ResponseEntity.ok().build();
    }

    // ==================== 2. ПОЛУЧЕНИЕ ДАННЫХ О СЕБЕ ====================

    @GetMapping("/me")
    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            description = "Возвращает данные текущего авторизованного пользователя: " +
                    "id, email, имя, фамилию, телефон и ссылку на аватар"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Данные пользователя успешно получены",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = User.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. Пользователь не авторизован",
                    content = @Content
            )
    })
    public ResponseEntity<User> getMe() {
        // Скелет: возвращаем пустой объект
        return ResponseEntity.ok().build();
    }

    // ==================== 3. ОБНОВЛЕНИЕ ДАННЫХ О СЕБЕ ====================

    @PatchMapping("/me")
    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            description = "Обновляет имя, фамилию и телефон текущего пользователя. " +
                    "Email, пароль и аватар обновляются отдельными эндпоинтами."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Данные успешно обновлены",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = UpdateUser.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. Пользователь не авторизован",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request. Ошибка валидации данных",
                    content = @Content
            )
    })
    public ResponseEntity<UpdateUser> updateMe(
            @Parameter(
                    description = "Объект с обновляемыми полями (имя, фамилия, телефон)",
                    required = true,
                    schema = @Schema(implementation = UpdateUser.class)
            )
            @RequestBody UpdateUser user
    ) {
        // Скелет: возвращаем то, что пришло
        return ResponseEntity.ok(user);
    }

    // ==================== 4. ОБНОВЛЕНИЕ АВАТАРА ====================

    @PatchMapping(value = "/me/image", consumes = {"multipart/form-data"})
    @Operation(
            summary = "Обновление аватара пользователя",
            description = "Загружает новый аватар для текущего пользователя. " +
                    "Поддерживаются форматы: JPEG, PNG, GIF. " +
                    "Максимальный размер файла: 10MB."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "OK. Аватар успешно обновлён"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Bad Request. Файл не загружен или имеет неверный формат",
                    content = @Content
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized. Пользователь не авторизован",
                    content = @Content
            )
    })
    public ResponseEntity<?> updateMeImage(
            @Parameter(
                    description = "Файл изображения (JPEG, PNG, GIF)",
                    required = true,
                    schema = @Schema(
                            type = "string",
                            format = "binary",
                            description = "Изображение для аватара"
                    )
            )
            @RequestParam("image") MultipartFile image
    ) {
        // Скелет с минимальной проверкой
        if (image == null || image.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        // Можно добавить логирование для отладки
        System.out.println("Получен файл: " + image.getOriginalFilename());
        System.out.println("Размер: " + image.getSize() + " байт");
        System.out.println("Тип: " + image.getContentType());

        return ResponseEntity.ok().build();
    }
}