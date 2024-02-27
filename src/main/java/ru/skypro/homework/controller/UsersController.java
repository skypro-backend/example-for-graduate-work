package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.security.logger.FormLogInfo;
import ru.skypro.homework.service.UserService;

import javax.validation.constraints.NotBlank;

@RequestMapping("/users")
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи")
@RestController
public class UsersController {
    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            tags = "Пользователи",
            summary = "Обновление пароля",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema())),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema()))
            }
    )
    @PostMapping(value = "/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        log.info("Обновление пароля___" + FormLogInfo.getInfo());
        userService.setPassword(newPassword, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(
            tags = "Пользователи",
            summary = "Получение информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content =
                            {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
            }
    )
    @GetMapping(value = "/me")
    public ResponseEntity<User> getUser(Authentication authentication) {
        log.info("Получение информации об авторизованном пользователе___" + FormLogInfo.getInfo());
        return ResponseEntity.ok(userService.getUser(authentication));
    }

    @Operation(
            tags = "Пользователи",
            summary = "Обновление информации об авторизованном пользователе",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content =
                            {@Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UpdateUser.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
            }
    )
    @PatchMapping(value = "/me")
    public ResponseEntity<UpdateUser> updateUser(
            @RequestBody
            @NotBlank(message = "updateUser не должен быть пустым") UpdateUser updateUser, Authentication authentication) {
        log.info("Обновление информации об авторизованном пользователе___" + FormLogInfo.getInfo());
        return ResponseEntity.ok(userService.updateUser(updateUser, authentication));
    }

    //переместился в ImageController

//    @Operation(
//            tags = "Пользователи",
//            summary = "Обновление аватара авторизованного пользователя",
//            responses = {
//                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema = @Schema())),
//                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = {@Content()})
//            }
//    )
//    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image,
//                                             Authentication authentication) {
//        log.info("Обновление аватара авторизованного пользователя___" + FormLogInfo.getInfo());
//
//        //userService.updateUserImage(image, authentication);
//
//        return ResponseEntity.ok().build();//ПУСТЫШКА
//    }
}