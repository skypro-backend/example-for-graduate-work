package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPasswordDto;
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
    public NewPasswordDto updateNewPassword(@RequestBody NewPasswordDto newPassword){
        return new NewPasswordDto();
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

    @PatchMapping ("/me/image")
    public UserDto updateUserImage(@RequestBody UserDto user) {

     return new UserDto();
            }




}
