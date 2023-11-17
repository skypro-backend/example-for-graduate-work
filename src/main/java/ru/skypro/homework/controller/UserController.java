package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

@RestController("/users")
public class UserController {
    UserService userService;
    AuthServiceImpl authService;

    public UserController(UserService userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }
    @PostMapping("/set_password") // http://localhost:3000/profile/users/set_password
    void setNewPassword(@RequestBody NewPassword newPass){
        userService.setNewPassword(newPass);
    }
}
