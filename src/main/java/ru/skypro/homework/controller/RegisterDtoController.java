package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.RegisterDto;

@RestController
@RequestMapping(name = "/register")
@CrossOrigin(value = "http://localhost:3000/")
public class RegisterDtoController {
    @PostMapping
    public RegisterDto registerUser(@RequestBody RegisterDto register) {
        return new RegisterDto();
    }
}
