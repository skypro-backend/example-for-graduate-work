package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    AuthServiceImpl authService;
    @Autowired
    UserService userService;

    @Operation(summary = "Обновление пароля", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden",
                    content = @Content)
    })
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(Authentication authentication,
                                         @RequestBody NewPasswordDTO newPassword) {
        if (authService.setPassword(authentication.getName(), newPassword)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Получить информацию об авторизованном пользователе\"я", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK",
                    content = @Content),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized",
                    content = @Content),
            @ApiResponse(responseCode = "403",
                    description = "Forbidden",
                    content = @Content)
    })
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserInfo(authentication.getName()));
    }

    @Operation(summary = "Обновить информацию об авторизованном пользователе", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = UserDTO.class))}),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized")
    })
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO,
                                                    Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(updateUserDTO, authentication.getName()));
    }

    @Operation(summary = "Обновить аватар авторизованного пользователя", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "OK"),
            @ApiResponse(responseCode = "401",
                    description = "Unauthorized")
    })
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image,
                                             Authentication authentication) throws IOException {
        userService.editUserImage(image, authentication.getName());
        return ResponseEntity.ok().build();
    }


}
