package ru.skypro.homework.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserController userService;


    // Получение пользователя по ID
    @GetMapping("/me")
    public User getUserById(@PathVariable int userId) {
        return new User(1, "fe", "fre", "vtgr", "f54", Role.USER.toString(), "/gfd/gtfr");
    }

    // Создание нового пользователя
    @PostMapping("/set_password")
    public void setPasswordUser() {
        System.out.println("Обновление пароля");
        new NewPassword("gtrji", "fregtr");
    }

    @PatchMapping("/me")
    public UpdateUser updateInfoUsers(@RequestBody User user) {
        System.out.println("Обновление информации об авторизованном пользователе");
        return new UpdateUser("btr", "reg", "rr");
    }

    @PatchMapping("/me/image")
    public void updateAvatarUsers( @PathVariable String pathImage) {
        System.out.println("Обновление avatar об авторизованном пользователе");
        System.out.println(pathImage);

    }
}
