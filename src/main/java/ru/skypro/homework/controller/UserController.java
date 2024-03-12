package ru.skypro.homework.controller;

import com.sun.istack.NotNull;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.CurrentUserService;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@Tag(name = "Пользователи")
public class UserController {

  private final CurrentUserService currentUserService;

    public UserController(CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
    }

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401"),
                    @ApiResponse(responseCode = "403")})
    public ResponseEntity<Void> setPassword(@RequestBody @Valid NewPassDto newPasswordDto,
                                            Authentication authentication) {
        if (currentUserService.setPassword(newPasswordDto, authentication)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    @Operation(summary = "Получить информацию об авторизованном пользователе")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401")})
    public ResponseEntity<UserDto> getUser(@NotNull Authentication authentication) {
        log.info("Запрос информации о пользователе");
        return ResponseEntity.ok(currentUserService.getUser(authentication));
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновить информацию об авторизованном пользователе")
    @ApiResponses(value =
            {@ApiResponse(responseCode = "200"),
                    @ApiResponse(responseCode = "401")})
    public ResponseEntity<UserDto> updateUser(@RequestBody @Valid UserDto userDto, Authentication authentication) {
        log.info("Нажали контроллер: Обновить информацию об авторизованном пользователе");
        return ResponseEntity.ok(currentUserService.updateUser(userDto, authentication));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновить аватар авторизованного пользователя")
    @ApiResponses(value = {@ApiResponse(responseCode = "200"),
            @ApiResponse(responseCode = "401"),
            @ApiResponse(responseCode = "403")})
    public ResponseEntity<Void> updateUserImage(@RequestPart @Valid MultipartFile image, Authentication authentication) {
        if (currentUserService.updateUserImage(image, authentication)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }
}