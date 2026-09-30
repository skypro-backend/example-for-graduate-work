package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    @Operation(summary = "Update password")
    @ApiResponse(responseCode = "200", description = "Password updated")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @ApiResponse(responseCode = "403", description = "Forbidden")
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(
            @RequestBody NewPassword newPassword,
            Authentication authentication
    ) {
        userService.changePassword(
                authentication.getName(),
                newPassword
        );

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get authorized user information")
    @ApiResponse(responseCode = "200", description = "User information returned")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @GetMapping("/me")
    public ResponseEntity<User> getUser(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                userService.getUser(
                        authentication.getName()
                )
        );
    }

    @Operation(summary = "Update authorized user information")
    @ApiResponse(responseCode = "200", description = "User information updated")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(
            @RequestBody UpdateUser updateUser,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                userService.updateUser(
                        authentication.getName(),
                        updateUser
                )
        );
    }

    @Operation(summary = "Update authorized user avatar")
    @ApiResponse(responseCode = "200", description = "Avatar updated")
    @ApiResponse(responseCode = "401", description = "Unauthorized")
    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(
            @RequestParam("image") MultipartFile image,
            Authentication authentication
    ) {
        userService.updateImage(
                authentication.getName(),
                image
        );

        return ResponseEntity.ok().build();
    }
}