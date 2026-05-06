package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.request.NewPasswordRequestDto;
import ru.skypro.homework.dto.request.UpdateUserRequestDto;
import ru.skypro.homework.dto.response.UserResponseDto;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

/**
 * REST-контроллер для работы с пользователями.
 * Позволяет:
 * - изменять пароль,
 * - получать профиль пользователя,
 * - обновлять профиль,
 * - загружать аватар.
 */
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Изменение пароля текущего пользователя.
     * @param dto данные для смены пароля
     */
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody @Valid NewPasswordRequestDto dto) {
        userService.setPassword(dto);
        return ResponseEntity.ok().build();
    }

    /**
     * Получение информации об авторизованном пользователе.
     * @return профиль пользователя
     */
    @GetMapping("/me")
    public ResponseEntity<UserResponseDto> getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    /**
     * Обновление данных профиля пользователя.
     * @param dto новые данные пользователя
     * @return обновлённый профиль
     */
    @PatchMapping("/me")
    public ResponseEntity<UserResponseDto> updateUser(@RequestBody @Valid UpdateUserRequestDto dto) {
        return ResponseEntity.ok(userService.updateUser(dto));
    }

    /**
     * Обновление аватара пользователя.
     * @param image файл изображения
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestPart("image") MultipartFile image) {
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }
}
