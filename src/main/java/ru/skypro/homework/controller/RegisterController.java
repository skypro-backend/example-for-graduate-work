package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.service.impl.RegisterServiceImpl;

@RestController
@RequestMapping(name = "/register")
@AllArgsConstructor
public class RegisterController {

    private final RegisterServiceImpl registerServiceImpl;

    @PostMapping
    public ResponseEntity<String> registerUser(@RequestBody Register register) {
        return registerServiceImpl.registerUser(register);
    }
}
