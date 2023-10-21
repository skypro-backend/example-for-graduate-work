package ru.skypro.homework.controller;


import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    // Получение пользователя
    @GetMapping("/me")
    public UserDTO getUser() {
        return new UserDTO(1, "fe", "fre", "vtgr", "f54", Role.USER.toString(), "/gfd/gtfr");
    }

    // Создание нового пользователя
    @PostMapping("/set_password")
    public void setPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        System.out.println("Обновление пароля");
        new NewPasswordDTO("gtrji", "fregtr");
    }

    @PatchMapping("/me")
    public UpdateUserDTO updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        System.out.println("Обновление информации об авторизованном пользователе");
        return new UpdateUserDTO("btr", "reg", "rr");
    }

    @PatchMapping("/me/image")
    public String updateUserImage( @PathVariable String pathImage) {
        System.out.println("Обновление avatar об авторизованном пользователе");
        return  "pathImage";

    }
}
