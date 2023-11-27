package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Login;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.UpdateAdService;

@RestController
@RequestMapping("/updateAd")
public class UpdateAdController {
    private UpdateAdService updateAdService;

    public void UpdateAdService(UpdateAdService updateAdService) {
        this.updateAdService = updateAdService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Login login) {
        if (updateAdService.login(login.getUsername(), login.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        if (updateAdService.register(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

