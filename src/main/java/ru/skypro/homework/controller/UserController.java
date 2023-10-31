package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.*;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

/**
 * Класс-контроллер для запуска эндпоинтов для пользователя
 * @autor Сулаева Марина
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * метод обновления пароля для зарегистрированного пользователя с проверкой валидности поданных значений
     */
    @PostMapping("/set_password")
    public void updatePassword(@RequestBody @Valid NewPassword newPassword, Authentication authentication) {
        userService.updatePassword(newPassword, authentication.getName());
    }
    /**
     * метод получение информации о своем профиле
     * для зарегистрированного пользователя
     */
    @GetMapping("/me")
    public User getInformation(Authentication authentication) {
        return userService.getInformation(authentication.getName());
    }

    /**
     * метод для обновления информации (имя, фамилия, телефон) зарегистрированного пользователя
     */
    @PatchMapping("/me")
    public UpdateUser updateInformationAboutUser(@RequestBody @Valid UpdateUser updateUser, Authentication authentication) {
        return userService.updateInformationAboutUser(updateUser, authentication.getName());
    }

    /**
     * метод для изменения фотографии в профиле зарегистрированного пользователя
     */
    @PatchMapping("/me/image")
    public ResponseEntity<byte []> updateImage(@RequestPart MultipartFile image, Authentication authentication) {
        userService.updateImage(image, authentication.getName());
        return ResponseEntity.ok().build();
    }
    /**
     * метод для вывода на экран фотографии в профиле зарегистрированного пользователя
     */
    @GetMapping(value ="/{id}/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte [] getImage(@PathVariable("id") String id) {
        return userService.getImage(id);
    }



}