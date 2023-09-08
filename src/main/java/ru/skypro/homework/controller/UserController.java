package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.entity.users.User;
import ru.skypro.homework.exceptions.PasswordMatches;
import ru.skypro.homework.exceptions.UserNotFoundEx;
import ru.skypro.homework.exceptions.UserNotUpdatedEx;
import ru.skypro.homework.service.users.UsersService;

import java.io.EOFException;
import java.io.IOException;

/**
 * Класс - контроллер для работы с авторизированным пользователем и его данными, содержащий набор API endpoints
 *
 * @see
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@Tag(name = "Пользователи")
@RequiredArgsConstructor
public class UserController {

    private final UsersService userService;

    @Operation(
            summary = "Обновление пароля пользователя",
            description = "Получение нового пароля пользователя из тела запроса"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ок. Новый пароль усешно установлен",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {
                            @Content(

                            )}

            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {
                            @Content(

                            )}

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = {
                            @Content(

                            )}
            )
    })
    @PostMapping("/{id}/set_password")

    public ResponseEntity setPassword(@PathVariable Integer id,@RequestBody NewPasswordDto newPasswordDto
    ) throws UserNotFoundEx, PasswordMatches {

        User user = userService.addNewPassword(id,newPasswordDto);
        try {
            return ResponseEntity.ok(user);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().body("false");
        }
    }


    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            description = "Для получения данных о пользоватееле ничего не нужно вводить"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ок. Данные о пользователе успешно получены",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {
                            @Content(

                            )}

            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {
                            @Content(

                            )}

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = {
                            @Content(

                            )}
            )
    })
    @GetMapping("/me")

    public ResponseEntity getUser(@RequestBody Integer id) throws UserNotFoundEx {
        User user = userService.getUser(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        System.out.println("Данные о пользователе получены");
        return ResponseEntity.ok(user);
    }

    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            description = "Обновление (получение) данных о пользователе через тело запроса"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ок. Данные о пользователе успешно обновлены",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UpdateUserDto.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Unauthorized",
                    content = {
                            @Content(

                            )}

            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Forbidden",
                    content = {
                            @Content(

                            )}

            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = {
                            @Content(

                            )}
            )
    })
    @PatchMapping("/me")

    public ResponseEntity updateUser(@RequestParam Integer id, @RequestBody UpdateUserDto userDto){

        try {
            return ResponseEntity.ok(userService.updateUser(id, userDto));
        } catch (UserNotUpdatedEx ex) {
            return   ResponseEntity.badRequest().body("User not updated");
        }
    }

    @Operation(
            summary = "Обновление аватара авторизованного пользователя",
            description = "Обновление аватара пользователя через тело запроса"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Ок. Аватар пользователя успешно обновлен",
                    content = {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    array = @ArraySchema(schema = @Schema(implementation = UserDto.class))
                            )
                    }
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Not found",
                    content = {
                            @Content(

                            )}
            )
    })
    @PostMapping(value = "/{id}/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity updateUserImage(@PathVariable Integer id, @RequestBody MultipartFile image ) throws IOException {
        log.info("Аватар пользователя успешно обновлен");


        try {
            userService.uploadImage(id, image);
            return ResponseEntity.ok()
                    .build();

        } catch (Exception e) {
            throw new EOFException("Не удалось загрузть файл");
        }

    }




}
