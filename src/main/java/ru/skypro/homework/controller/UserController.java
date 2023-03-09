package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.impl.UserServiceImpl;

import javax.validation.Valid;
import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@Validated
@RequestMapping(value = "users")
public class UserController {
    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @Operation(summary = "setPassword", description = "Установка пароля", tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = NewPassword.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PostMapping(value = "set_password",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<NewPassword> setPassword(NewPassword body) {
        return ResponseEntity.ok(userService.setPassword(body));
    }

    @Operation(summary = "getUser", description = "Получение пользователя", tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @GetMapping(value = "me", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUsers(authentication.getName()));
    }

    @Operation(summary = "updateUser", description = "Изменение пользователя", tags = {"Пользователи"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    schema = @Schema(implementation = User.class))),
            @ApiResponse(responseCode = "204", description = "No Content"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PatchMapping(value = "me", produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<User> updateUser(Authentication authentication,
            @Parameter(in = ParameterIn.DEFAULT, required = true, schema = @Schema()) @Valid @RequestBody User body) {
        return ResponseEntity.ok(userService.updateUser(authentication.getName(), body));
    }

    @Operation(summary = "updateUserImage", description = "UpdateUserImage", tags = "Пользователи")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "404", description = "Not Found")})
    @PatchMapping(value = "/me/image", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<Void> updateUserImage(Authentication authentication, MultipartFile image) throws IOException {
        return userService.updateUserImage(authentication.getName(), image);
    }
}