package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.UserEntity;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AuthServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

@RestController
@Tag(name = "\uD83D\uDE4B Пользователи")
@CrossOrigin("http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private UserService userService;
    private AuthServiceImpl authService;

    public UserController(UserService userService, AuthServiceImpl authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @Operation(
            tags = "Пользователи",
            summary = "Обновление пароля",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Пароль обновлен",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Пользователь не авторизован (unauthorized)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Доступ запрещен (forbidden)",
                            content = @Content()
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Пользователь не найден (not found)",
                            content = @Content()
                    )
            }
    )

    @PostMapping("/set_password") // http://localhost:8080/users/set_password
    public ResponseEntity setPassword(@RequestBody NewPassword newPass, Authentication authentication) {
        userService.setPassword(newPass, authentication);
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
