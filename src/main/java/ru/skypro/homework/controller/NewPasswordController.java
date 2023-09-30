package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.NewPassword;

@RestController
@RequestMapping("/password")
@CrossOrigin(value = "http://localhost:3000")
public class NewPasswordController {

    public NewPassword password(@RequestBody NewPassword password) {
        return new NewPassword();
    }
}
