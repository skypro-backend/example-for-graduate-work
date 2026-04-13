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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Tag(name = "Пользователи", description = "API для управления пользователями")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            description = "Возвращает данные текущего пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Успешное получение данных",
                    content = @Content(schema = @Schema(implementation = User.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<User> getUser(Authentication authentication) {
        log.info("Getting user info for: {}", authentication.getName());

        User user = userService.getUserByEmail(authentication.getName());

        if (user.getImage() != null && !user.getImage().isEmpty()) {
            user.setImage(user.getImage() + "?v=" + System.currentTimeMillis());
        }

        return ResponseEntity.ok(user);
    }

    @PatchMapping("/me")
    @Operation(
            description = "Обновляет данные текущего пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Данные успешно обновлены",
                    content = @Content(schema = @Schema(implementation = UpdateUser.class))
            ),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser,
                                                 Authentication authentication) {
        log.info("Updating user info for: {}", authentication.getName());

        User updatedUser = userService.updateUser(authentication.getName(), updateUser);

        UpdateUser response = new UpdateUser();
        response.setFirstName(updatedUser.getFirstName());
        response.setLastName(updatedUser.getLastName());
        response.setPhone(updatedUser.getPhone());

        return ResponseEntity.ok(response);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            description = "Загружает новый аватар для текущего пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аватар успешно обновлен"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image,
                                                Authentication authentication) {
        log.info("Updating avatar for user: {}", authentication.getName());
        userService.updateUserImage(authentication.getName(), image);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me/image")
    @Operation(
            description = "Возвращает изображение аватара текущего пользователя"
    )
    public ResponseEntity<byte[]> getUserImage(Authentication authentication) {
        byte[] image = userService.getUserImage(authentication.getName());

        if (image == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .header("Cache-Control", "no-cache, no-store, must-revalidate")
                .body(image);
    }
}