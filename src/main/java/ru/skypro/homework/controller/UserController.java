package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@RestController
public class UserController {


    @GetMapping("/users/{id}")
    public User getUser(@PathVariable int id) {
        return new User(); // Возвращает пустой объект User
    }

    @PatchMapping("/users/{id}")
    public User updateUser(@PathVariable int id, @RequestBody UpdateUser user) {
        return new User(); // Возвращает пустой объект User
    }
}
