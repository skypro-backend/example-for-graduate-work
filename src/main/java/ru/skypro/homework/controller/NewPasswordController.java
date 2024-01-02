package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.exception.NotAllowedPasswordException;
import ru.skypro.homework.service.impl.NewPasswordImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RestController
public class NewPasswordController {

    private final NewPasswordImpl newPasswordService;

    private final Logger logger = LoggerFactory.getLogger(NewPasswordImpl.class);

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPassword) {
        if (newPassword.getNewPassword().length() <= 8 || newPassword.getNewPassword().length() >= 16) {
            throw new NotAllowedPasswordException("Пароль не соответсвует требованиям");
        } else {
            newPasswordService.setPassword(newPassword.getCurrentPassword(),
                    newPassword.getNewPassword());
            return ResponseEntity.ok().build();
        }
    }
}
