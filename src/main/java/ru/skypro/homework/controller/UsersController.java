package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import java.security.Principal;
/**
 * Контроллер для работы с пользователем.
 * Позволяет получать и обновлять данные пользователя,
 * изменять пароль и обновлять изображение.
 */
@RestController
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    public UsersController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Контроллер для работы с пользователем.
     * Позволяет получать и обновлять данные пользователя,
     * изменять пароль и обновлять изображение.
     */
    @PostMapping("/set_password")
    public void setPassword(@RequestBody NewPassword request,
                            Principal principal) {
        userService.changePassword(principal.getName(), request);
    }

    /**
     * Получает данные текущего пользователя.
     *
     * @param principal текущий пользователь
     * @return пользователь
     */
    @GetMapping("/me")
    public User getUser(Principal principal) {
        return userService.getUser(principal.getName());
    }

    /**
     * Получает данные текущего пользователя.
     *
     * @param principal текущий пользователь
     * @return пользователь
     */
    @PatchMapping("/me")
    public User updateUser(@RequestBody User request,
                           Principal principal) {
        return userService.updateUser(principal.getName(), request);
    }

    /**
     * Обновляет изображение пользователя.
     *
     * @param principal текущий пользователь
     */
    @PatchMapping("/me/image")
    public void updateUserImage(Principal principal) {
        userService.updateUserImage(principal.getName());
    }
}

