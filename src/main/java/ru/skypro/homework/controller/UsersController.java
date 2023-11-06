package ru.skypro.homework.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")

public class UsersController {
   private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Получить информацию об авторизованном пользователе")
    @ApiResponses(value = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "401")})
    public ResponseEntity<UserDto> getUser(@NotNull Authentication authentication) {
        log.info("Запрос информации о пользователе");
        return ResponseEntity.ok(userService.getUser(authentication));
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновить информацию об авторизованном пользователе")
    @ApiResponses(value = {@ApiResponse(responseCode = "200"), @ApiResponse(responseCode = "401")})
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto, Authentication authentication) {
        log.info("Обновить информацию об авторизованном пользователе");
        return ResponseEntity.ok(userService.updateUser(userDto, authentication));
    }

    @PatchMapping(path = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDto updateUserImage(@RequestPart(name = "image") MultipartFile image) {
        return new UserDto();
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновить аватар авторизованного пользователя")
    @ApiResponses(value = {@ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")})
    public ResponseEntity<Void> updateUserImage(@RequestPart @Valid MultipartFile image, Authentication authentication) {
        if (userService.updateUserImage(image, authentication)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Operation(summary = "Обновление пароля")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content = @Content()),
            @ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content()),
            @ApiResponse(responseCode = "403", description = "Forbidden", content = @Content()),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content())
    })
    public ResponseEntity<Void> setPassword(@RequestBody @Valid NewPasswordDto newPasswordDto,
                                            Authentication authentication) {
        if (userService.setPassword(newPasswordDto, authentication)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
