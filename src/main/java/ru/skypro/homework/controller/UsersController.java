package ru.skypro.homework.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            description = "Возвращает полную информацию о пользователе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @GetMapping("/me")
    public ResponseEntity<User> getUser(Authentication authentication) {
        User user = userService.getUser(authentication);
        return ResponseEntity.ok(user);
    }


    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            description = "Обновляет информацию об авторизованном пользователе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
        userService.updateUser(authentication, updateUser);
        return ResponseEntity.ok(updateUser);

    }


    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            description = "Обновляет аватар авторизованного пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @PatchMapping("/me/image")
    public ResponseEntity<Resource> updateUserImage(@RequestParam("image") MultipartFile image, Authentication authentication) {
        Resource resource = userService.updateUserImageAndReturn(authentication, image);
        return ResponseEntity.ok()
                .contentType(MediaType.IMAGE_JPEG)
                .body(resource);
    }


    @Operation(
            summary = "Обновление пароля",
            description = "Обновляет пароль пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "Ok"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        userService.changePassword(authentication, newPassword);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
