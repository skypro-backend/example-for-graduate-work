package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final ImageService imageService;

    @Operation(summary = "setPassword",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @PreAuthorize("isAuthenticated()")

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto, Authentication authentication) {
        log.info("Was invoked set password for user method");
        userService.updatePassword(
                authentication.name(),
                newPasswordDto);
        return ResponseEntity.ok().build();

    }

    @Operation(summary = "getUser",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    schema = @Schema(implementation = UserDto.class)
                            )
                    ),
                    @ApiResponse(responseCode = "404", description = "Not Found")
            })
    @PreAuthorize("isAuthenticated()")
    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(Authentication authentication) {
        log.info("Was invoked get user method");
        return ResponseEntity.ok(userService.getUserInformation(authentication.name()));
    }

    @Operation(summary = "updateUser",
            description = "update info about user. return updated user",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = UserDto.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})

    @PreAuthorize("isAuthenticated()")
    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UpdateUserDto updateUserDto,
                                              Authentication authentication) {
        log.info("Was invoked update user method");
        return ResponseEntity.ok(userService.updateUser(authentication.name(), updateUserDto));
    }

    @Operation(
            summary = "updateUserImage",
            description = "UpdateUserImage",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "401", description = "Unauthorized")})
    @PreAuthorize("isAuthenticated()")
    @PatchMapping(value = "/me/image", consumes =  {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> image(@RequestBody MultipartFile image,
                                      Authentication authentication) {
        userService.updateUserAvatar(authentication.name(), image);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "getUserAvatar",
            tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_OCTET_STREAM_VALUE,
                    array = @ArraySchema(schema = @Schema(implementation = byte[].class)))),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PreAuthorize("isAuthenticated()")

    @GetMapping(value = "me/image/", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})

    public byte[] getImage(@PathVariable("id") String id) {
        return imageService.getImage(id);
    }
}
