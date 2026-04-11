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
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
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

        return userService.getUserByEmail(authentication.getName())
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }

    @PatchMapping("/me")
    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
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

        return userService.updateUser(authentication.getName(), updateUser)
                .map(user -> {
                    UpdateUser response = new UpdateUser();
                    response.setFirstName(user.getFirstName());
                    response.setLastName(user.getLastName());
                    response.setPhone(user.getPhone());
                    return ResponseEntity.ok(response);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            description = "Загружает новый аватар для текущего пользователя"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Аватар успешно обновлен"),
            @ApiResponse(responseCode = "401", description = "Пользователь не авторизован")
    })
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image,
                                                Authentication authentication) {
        log.info("Updating avatar for user: {}", authentication.getName());

        String imageUrl = "/images/avatars/" + authentication.getName() + ".jpg";
        userService.updateUserImage(authentication.getName(), imageUrl);

        return ResponseEntity.ok().build();
    }
}