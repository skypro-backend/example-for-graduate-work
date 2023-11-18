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
    public ResponseEntity setNewPassword(@RequestBody NewPassword newPass){
        userService.setNewPassword(newPass);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me") // http://localhost:8080/users/me
    public User getUserInfo(){
        User user = UserMapper.mapToUserDto(userService.getUserInfo());
        return user;
    }

    @PatchMapping("/me") // http://localhost:8080/users/me
    public ResponseEntity updateUserInfo(@RequestBody UpdateUser updateUser){
        UserEntity user = userService.updateUserInfo(updateUser);
        if (user != null) {
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
