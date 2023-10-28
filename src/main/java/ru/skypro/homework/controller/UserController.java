package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.AdsUserDetails;
import ru.skypro.homework.projections.NewPassword;
import ru.skypro.homework.projections.UpdateUser;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.util.Objects;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final UserRepo userRepo; // так... потестить

    // Получение пользователя
    @GetMapping("/me")
    public UserDTO getUser(Authentication authentication) {
//        return ResponseEntity.ok(new UserDTO(0, "fe@mail.ru", "nameForTest", "LastnameForTest", "+79999999999", Role.USER.name(), null));
        return userService.getUser(authentication);
    }

    // Создание нового пользователя
    @PostMapping("/set_password")
    public void setPassword(@RequestBody NewPassword newPassword) {
        System.out.println("Обновление пароля");
        new NewPassword("gtrji", "fregtr");
    }

    @PatchMapping("/me")
    public UpdateUser updateUser(@RequestBody UpdateUser updateUser) {
        System.out.println("Обновление информации об авторизованном пользователе");
        return new UpdateUser("btr", "reg", "rr");
    }

    @PatchMapping("/me/image")
    public String updateUserImage(@PathVariable String pathImage) {
        System.out.println("Обновление avatar об авторизованном пользователе");
        return "pathImage";

    }
}
