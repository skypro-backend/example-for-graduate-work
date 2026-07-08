package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.user.NewPassword;
import ru.skypro.homework.user.UpdateUser;
import ru.skypro.homework.user.User;

import javax.validation.Valid;

        //      * Контроллер личного кабинета пользователя.
        //      * Содержит методы для просмотра и редактирования собственного профиля, смены пароля и загрузки аватара.

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    @Operation(summary = "Профиль пользователя")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> getCurrentUser(Authentication auth) {
        return ResponseEntity.ok(userService.getCurrentUser(auth));
    }

    @PatchMapping("/me")
    @Operation(summary = "Редактирование профиля")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<User> updateUser(
            @RequestBody UpdateUser data,
            Authentication auth) {
        return ResponseEntity.ok(userService.updateProfile(data, auth));
    }

    @PostMapping("/set_password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<NewPassword> changePassword(
            @Valid @RequestBody NewPassword data,
            Authentication auth) {
        userService.changePassword(data, auth);
        return ResponseEntity.ok(new NewPassword());
    }

    @PatchMapping("/me/image")
    @Operation(summary = "Загрузка аватара")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> uploadAvatar(
            @RequestPart MultipartFile image,
            Authentication auth) {

        userService.uploadAvatar(image, auth);
        return ResponseEntity.noContent().build();
    }
}