package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

import ru.skypro.homework.entity.PhotoEntity;
import ru.skypro.homework.service.*;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    @Operation(
            summary = "Change password",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Password changed successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPasswordDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Error when changing password",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = NewPasswordDTO.class)
                            )
                    )
            }
    )
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDTO newPasswordDTO, Authentication authentication) {
        userService.changePassword(newPasswordDTO, authentication);
        return ResponseEntity.ok().build();
    }
    @Operation(
            summary = "Get an authorized user",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Authorized user has been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Authorized user has not been received",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            }
    )
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
            return ResponseEntity.ok(userService.getAuthorizedUserDto(authentication));

    }
    @Operation(
            summary = "Update user information",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User data updated successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "An error occurred while updating data",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = UserDTO.class)
                            )
                    )
            }
    )
    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO, Authentication authentication) {
            return ResponseEntity.ok(userService.updateUser(userDTO, authentication));

    }
    @Operation(
            summary = "User avatar update",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User avatar updated successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PhotoEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User avatar update error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PhotoEntity.class)
                            )
                    )
            }
    )
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image, Authentication authentication) throws IOException {
        userService.updateAvatar(image, authentication);
        return ResponseEntity.ok().build();
    }
    private boolean isValidPassword(NewPasswordDTO newPasswordDTO) {
        return newPasswordDTO.getCurrentPassword().length() >= 5
                && newPasswordDTO.getNewPassword().length() >= 5; // Проверка длины
    }

    @Operation(
            summary = "Getting a user's avatar",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "User avatar received successfully",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PhotoEntity.class)
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User avatar getting error",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = PhotoEntity.class)
                            )
                    )
            }
    )
    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable long id) throws IOException {
        log.info("Get user image with id" + id);
        return ResponseEntity.ok(userService.getUserImage(id));
    }
}