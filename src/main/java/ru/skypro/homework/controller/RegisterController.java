package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.impl.RegisterServiceImpl;

@RestController
@RequestMapping(name = "/register")
@CrossOrigin(value = "http://localhost:3000/")
public class RegisterController {
    @PostMapping
    public Register registerUser(@RequestBody Register register) {
        return new Register();
    }
}
