package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UserDTO;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    //Изменить пароль
    @PostMapping("/set_password")
    public UserDTO setPassword(@RequestBody String newPassword) {
        return null;
    }

    //Информация о пользователе
    @GetMapping("/me")
    public UserDTO getUserInfo() {
        return null;
    }

    //Обновить информацию о пользователе
    @PatchMapping("/me")
    public UserDTO updateUserInfo(UserDTO userDTO) {
        return null;
    }

    //Обновить изображение о пользователе
    @PatchMapping("/me/image")
    public UserDTO updateUserImage(UserDTO userDTO) {
        return null;
    }
}
