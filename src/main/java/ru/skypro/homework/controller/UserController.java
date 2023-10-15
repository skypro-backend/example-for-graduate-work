package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;

/**
 * Контроллер для работы с пользователями
 *
 */

@RestController
@RequestMapping("/users")


    @CrossOrigin(value = "http://localhost:3000")
    public class UserController{

   @Operation (
            summary = "Обновление пароля",
    tags= "Пользователи")

    @PostMapping("/set_password")
    public NewPassword updateNewPassword(@RequestBody NewPassword newPassword){
        return new NewPassword();
    }

    @Operation(
            summary = "Получение информации об авторизованном пользователе",
            tags="Пользователи"
    )
    @GetMapping("/me")
    public UserDto getUser(@RequestBody UserDto user){

        return new UserDto();
    }
    @Operation(
            summary = "Обновление информации об авторизованном пользователе",
            tags =  "Пользователи"
    )

    @PatchMapping("/me")
    public UserDto updateUser(@RequestBody UserDto user) {

        System.out.println("hello");
        return new UserDto();

    }
    @Operation(
           summary= "Обновление аватара авторизованного пользователя",
            tags =  "Пользователи"
    )

    @PatchMapping (value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public byte[] updateUserImage(@RequestParam("image") MultipartFile image){
        return null;
   }




}
