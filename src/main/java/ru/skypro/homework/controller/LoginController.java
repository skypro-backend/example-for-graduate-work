package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.LoginDto;

@RestController
@RequestMapping("/login")
public class LoginController {
    @PostMapping
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto) {
        return ResponseEntity.ok().build();
    }
}
