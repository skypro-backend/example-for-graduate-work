package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
public class PasswordController {
    private final UserService userService;

    public PasswordController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_USER')")
    @PatchMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        userService.updatePassword(newPasswordDto);
        return ResponseEntity.ok().build();
    }
}
