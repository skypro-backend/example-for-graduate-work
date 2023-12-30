package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;



@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@CrossOrigin(value = "http://localhost:3000")
public class UsersController {


    private final UserService service;
    private final ImageService imageService;

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
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDTO newPasswordDTO, Authentication authentication) {
        service.setPassword(newPasswordDTO,authentication);
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
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        service.getUser(authentication);
        return ResponseEntity.ok(service.getUser(authentication));
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
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO, Authentication authentication){
        return ResponseEntity.ok(service.updateUserInfo(updateUserDTO,authentication));
    }

    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Аватар изменен",
                    content = @Content(
                    )


            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized"

            )

    })
    @PatchMapping(value = "/me/image",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя", description = "updateAvatarUser", tags = {"Пользователи"})
    public ResponseEntity<Void> changeUserAvatar(@RequestPart MultipartFile image, Authentication authentication){
        service.updateUserAvatar(image,authentication);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получение аватара пользователя", description = "getImage", tags = {"Пользователи"})
    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable long id) {
        return ResponseEntity.ok(imageService.getImage(id).getData());
    }
}
