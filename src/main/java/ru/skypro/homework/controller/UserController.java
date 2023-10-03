package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDto;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "/http://localhost:3000")
public class UserController{
    @PatchMapping("/me")
    public UserDto updateUser(@RequestBody UserDto user) {

        System.out.println("hello");
        return new UserDto();
    }
}
