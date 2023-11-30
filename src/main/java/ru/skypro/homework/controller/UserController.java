package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private AuthServiceImpl authService;

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

    //    @PatchMapping("/me") // http://localhost:8080/users/me
//    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
//        UserEntity user = userService.updateUser(updateUser, authentication);
//        if (user != null) {
//            return ResponseEntity.ok(UserMapper.mapFromUserEntityToUpdateUser(user));
//        }else{
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }
//    @PatchMapping("/me/image")
//    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image) throws IOException {
//
//        if (authService.getLogin() != null) {
//            if (userService.updateUserImage(image)) {
//                return ResponseEntity.ok().build();
//            } else {
//                return null;
//            }
//        } else {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//    }

}
