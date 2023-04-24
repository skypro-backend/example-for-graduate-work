
package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {
    private final UserService userService;

    @Operation(
            summary = "Обновление пароля", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = NewPassword.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword( @RequestBody NewPassword newPassword, Authentication authentication ) {
        NewPassword resultPass = new NewPassword();
        userService.changePassword(authentication.getName(), newPassword.getCurrentPassword(), newPassword.getNewPassword(), authentication);
        resultPass.setCurrentPassword(newPassword.getCurrentPassword());
        resultPass.setNewPassword(newPassword.getNewPassword());
        return ResponseEntity.ok(resultPass);
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @GetMapping("/me")
    public ResponseEntity<User> getUserInfo( Authentication authentication ) {
        User founded = userService.getUser(authentication);
        return ResponseEntity.ok(founded);
    }


    @GetMapping("/{id}/avatar")
    public ResponseEntity<byte[]> getUsersAvatar( @PathVariable Integer id ) {
        byte[] founded = userService.getAvatarByUserId(id);
        return ResponseEntity.ok(founded);
    }

    @Operation(
            summary = "Обновление информации об авторизованном пользователе", tags = "Пользователи",
            responses = {
                    @ApiResponse(
                            responseCode = "200", description = "OK",
                            content = {@Content(mediaType = "application/json",
                                    schema = @Schema(implementation = User.class))}),
                    @ApiResponse(responseCode = "204", description = "No Content", content = @Content),
                    @ApiResponse(responseCode = "401", description = "Unauthorised", content = @Content),
                    @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<User> updateUser( @RequestBody User user, Authentication authentication ) {
        User result = userService.updateUser(user, authentication);
        return ResponseEntity.ok(result);
    }

    @Operation(
            summary = "Обновление аватара авторизованного пользователя", tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content),
                    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
            }
    )
    @PatchMapping("/me/image")
    public ResponseEntity<?> updateUserImage( @RequestBody MultipartFile image, Authentication authentication ) {
        userService.updateUserImage(image, authentication);
        return ResponseEntity.ok().build();
    }
}
