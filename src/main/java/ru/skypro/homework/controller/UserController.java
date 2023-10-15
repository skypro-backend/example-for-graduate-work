package ru.skypro.homework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;

import java.util.List;
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {


    // Получение пользователя
    @GetMapping("/me")
    public User getUser() {
        return new User(1, "rrr", "Iri", "Romanov", "*89845", Role.USER.toString(), "gfmtreo/trbvt");
//        return new User(0, "fe", "Obito", "Rim", "f55", Role.USER.toString(), "/gfd/gtfr");
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
