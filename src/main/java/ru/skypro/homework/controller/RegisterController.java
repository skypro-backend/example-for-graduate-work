package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Registred;
import ru.skypro.homework.service.impl.RegistredServiceImpl;

@AllArgsConstructor
public class RegisterController {
    private final RegistredServiceImpl registredService;

    @PutMapping(value = "/registred")
    public void create(Registred registred) {
        try {
            registredService.create(registred);
        } catch (ResponseStatusException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }
}
