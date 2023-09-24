package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.service.UserService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDto newPasswordDto, Authentication authentication) {
        org.springframework.security.core.Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
        userService.updatePassword(authentication1.getName(), newPasswordDto);
//запрос в сервис
        return ResponseEntity.ok().build();

    }

    @GetMapping("/me")
    public ResponseEntity<?> userDTO() {
//запрос в сервис
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto updateUser) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> image(@RequestBody String image) {
//запрос в сервис
        return ResponseEntity.ok().build();
    }
}
