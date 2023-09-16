package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
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
import ru.skypro.homework.dto.AdsDto;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AvatarServiceImpl;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи", description = "UserController")
public class UserController {

    private final UserService userService;
    private final AvatarServiceImpl avatarService;

    @Operation(
            summary = "Получение информации об авторизованном пользователе", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = UserDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            }
    )

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.get(authentication));
    }


    @Operation(
            summary = "Обновление информации об авторизованном пользователе", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdsDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto,
                                              Authentication authentication) {
        return ResponseEntity.ok((userService.update(userDto, authentication)));

    }

    @Operation(
            summary = "Обновление пароля", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = AdsDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content)
            }
    )
    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword,
                                                   Authentication authentication) {
        userService.updatePassword(newPassword, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Обновление аватара авторизованного пользователя", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "multipart/form-data",
                                    schema = @Schema(implementation = UserDto.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content)
            }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserAvatar(@RequestPart("image") MultipartFile avatarFile,
                                                   Authentication authentication) throws IOException {
        userService.updateAvatar(avatarFile, authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(hidden = true)
    @GetMapping(value = "/avatar/{id}", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] getAvatar(@PathVariable("id") Integer id) {

        return avatarService.getImageById(id).getData();
    }
}