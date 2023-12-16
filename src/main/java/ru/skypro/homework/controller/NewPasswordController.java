package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.exception.NotAllowedPasswordException;
import ru.skypro.homework.service.NewPasswordService;

@RequiredArgsConstructor
@RestController
public class NewPasswordController {
    private final NewPasswordService newPasswordService;

    /*public NewPasswordController(NewPasswordService newPasswordService) {
        this.newPasswordService = newPasswordService;
    }*/

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        if (newPassword.getNewPassword().length() <= 8 || newPassword.getNewPassword().length() >= 16) {
            throw new NotAllowedPasswordException("Пароль не соответсвует требованиям");
        } else {
            newPasswordService.setPassword(newPassword.getCurrentPassword(),
                    newPassword.getConfirmPhoneNumber(),
                    newPassword.getNewPassword());
            return ResponseEntity.ok().build();
        }
    }
}
