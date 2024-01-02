package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping(path = "http://localhost:8080/register")
@Tag(name = "RegistrationController does nothing while Registration with implement logic. Delete?")
public class RegistrationController {
    @PostMapping
    @Operation(description = "Does nothing. Delete it?")
    public void register(Authentication authentication) {

    }
}
