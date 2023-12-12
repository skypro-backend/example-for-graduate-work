package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UserDetailsManager userDetailsService;


    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword){
        userDetailsService.changePassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
        return ResponseEntity.ok().build();

    }
    @GetMapping("/me")
    public ResponseEntity<User> getUser(){
        return null;
    }
}
