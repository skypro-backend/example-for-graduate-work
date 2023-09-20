package ru.skypro.homework.controller;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.exeptions.ExceptionService;
import ru.skypro.homework.exeptions.ForbiddenException;
import ru.skypro.homework.exeptions.UnauthorizedException;

import javax.transaction.Transactional;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@Transactional
@RequestMapping("/users")
public class UserController {

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO user) {

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();

        if (!isUserAuthenticated()) {
            throw new UnauthorizedException("Вы не прошли авторизацию");
        }

        return ResponseEntity.ok().body(updateUserDTO);
    }

    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDTO> updatePassword(@RequestBody NewPasswordDTO user) {

        NewPasswordDTO newPasswordDTO = new NewPasswordDTO();

        if (!isUserAuthorized()) {
            throw new ForbiddenException("Доступ запрещен");
        }

        if (!isUserAuthenticated()) {
            throw new UnauthorizedException("Вы не прошли авторизацию");
        }

        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(@RequestBody UserDto user) {

        UserDto userDto = new UserDto();

        if (!isUserAuthenticated()) {
            throw new UnauthorizedException("Вы не прошли авторизацию");
        }

        return ResponseEntity.ok().body(userDto);
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> updateUserAvatar(@RequestBody String image) {

        if (!isUserAuthenticated()) {
            throw new UnauthorizedException("Вы не прошли авторизацию");
        }

        return ResponseEntity.ok().build();
    }

    // 401 Unauthorized (Неавторизован):
    private boolean isUserAuthenticated() {
        //Проверки аутентификации пользователя
        return true;
    }

    // 403 Forbidden (Запрещено):
    private boolean isUserAuthorized() {
        // Ппроверка авторизации пользователя
        return true;
    }
}
