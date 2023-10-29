package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.NewPasswordDto;
import ru.skypro.homework.dto.model_dto.UpdateUserDto;
import ru.skypro.homework.dto.model_dto.UserDto;

import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;
import java.io.IOException;

/**
 * Класс-контроллер для обработки запросов, связанных с пользователями.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Tag (name = "Пользователи", description = "работа с пользователями")
public class UserController {

    private final UserService userService;
    private final ImageService imageService;

    @Operation(summary = "Обновление пароля", responses = {
              @ApiResponse (responseCode = "200", description = "OK"),
              @ApiResponse(responseCode = "401", description = "Unauthorized"),
              @ApiResponse(responseCode = "403", description = "Forbidden")
    })
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody @Valid NewPasswordDto newPasswordDto,
                                         Authentication authentication) {
        userService.newPassword (newPasswordDto, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение информации об авторизованном пользователе", responses = {
              @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content (mediaType = "application/json", array = @ArraySchema (schema =
                        @Schema (implementation = UserDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.findAuthUser (authentication));
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе", responses = {
              @ApiResponse(responseCode = "200", description = "OK", content = {
                        @Content(mediaType = "application/json", array = @ArraySchema(schema =
                        @Schema(implementation = UpdateUserDto.class)))}),
              @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUserImage(@RequestBody @Valid UpdateUserDto updateUserDto,
                                                    Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser (updateUserDto, authentication));
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя", responses = {
              @ApiResponse(responseCode = "200", description = "OK"),
              @ApiResponse(responseCode = "401", description = "Unauthorized")
    })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UpdateUserDto> updateUserImage(@RequestPart("image")  MultipartFile multipartFile,
                                                         Authentication authentication) throws IOException {
        userService.updateAvatar(multipartFile, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/image/{id}")
    @Operation(summary = "Получение изображения пользователя", responses = {
              @ApiResponse(responseCode = "200", description = "OK")
    })
    public ResponseEntity<byte[]> getImage(@PathVariable int id) {
        return ResponseEntity.ok(imageService.getImage(id));
    }
}
