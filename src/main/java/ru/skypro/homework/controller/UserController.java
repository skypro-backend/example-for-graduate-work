package ru.skypro.homework.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.model.Role;
import ru.skypro.homework.projections.NewPassword;
import ru.skypro.homework.projections.UpdateUser;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    // Получение пользователя
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {

//        return new UserDTO(0, "fe", "fre", "vtgr", "f54", Role.USER.name(), null);
        return ResponseEntity.ok(new UserDTO(0, "fe@mail.ru", "nameForTest", "LastnameForTest", "+79999999999", Role.USER.name(), null));
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
