package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.RegisterDto;
import ru.skypro.homework.service.RegisterService;

@RestController
@RequestMapping(path = "/register")
@CrossOrigin(value = "http://localhost:3000")
@AllArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto register) {
        if (registerService.registerUser(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
