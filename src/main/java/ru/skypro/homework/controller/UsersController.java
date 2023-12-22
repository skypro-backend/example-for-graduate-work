package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;


@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Пароль успешно обновлен"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden"
            )
    })
    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля", description = "setPassword", tags = {"Пользователи"})
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        return ResponseEntity.ok().build();

    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация получена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class)
                    )

            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            )
    })
    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе", description = "getUser", tags = {"Пользователи"})
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(new UserDTO());
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Информация обновлена",
                    content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDTO.class)
                    )


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            )

    })
    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе", description = "updateUser", tags = {"Пользователи"})
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO){
        return ResponseEntity.ok(new UpdateUserDTO());
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Аватар изменен"


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            )

    })
    @PatchMapping(value = "/me",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Получение информации об авторизованном пользователе", description = "getUser", tags = {"Пользователи"})
    public ResponseEntity<Void> changeUserAvatar(@RequestBody MultipartFile image){
        return ResponseEntity.ok().build();
    }
}
