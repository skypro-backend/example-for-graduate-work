package ru.skypro.flea.controller.impl;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.flea.controller.AuthApi;
import ru.skypro.flea.dto.LoginDto;
import ru.skypro.flea.dto.RegisterDto;
import ru.skypro.flea.service.AuthService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class AuthApiController implements AuthApi {

    private final AuthService authService;

    @Override
    @Tag(name = "Authorization")
    public ResponseEntity<Void> login(LoginDto login) {
        if (authService.login(login.getUsername(), login.getPassword())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @Override
    @Tag(name = "Registration")
    public ResponseEntity<Void> register(RegisterDto register) {
        if (authService.register(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
