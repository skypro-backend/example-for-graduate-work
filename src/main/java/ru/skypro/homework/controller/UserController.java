package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
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

    @PostMapping("/set_password") // http://localhost:8080/users/set_password
    public ResponseEntity setPassword(@RequestBody NewPassword newPass) {
        userService.setPassword(newPass);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me") // http://localhost:8080/users/me
    public ResponseEntity<User> getUser() {
        UserEntity user = userService.getUser();
        if (user != null) {
            return ResponseEntity.ok(UserMapper.mapFromUserEntityToUser(user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping("/me") // http://localhost:8080/users/me
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        UserEntity user = userService.updateUser(updateUser);
        if (user != null) {
            return ResponseEntity.ok(UserMapper.mapFromUserEntityToUpdateUser(user));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
