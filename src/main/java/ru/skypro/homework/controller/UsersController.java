package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.dto.UserUpdateReq;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.UserServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    /**
     * Обновление пароля пользователя
     */
    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(
            @RequestBody NewPassword newPassword)
    {
        userService.updateUserPassword(newPassword);
        return ResponseEntity.ok(newPassword);
    }

    /**
     Получение информации о пользователе
     */
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        UserDTO userDTO = null;
        try {
            userDTO = userService.getUser();
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }
        return ResponseEntity.ok().body(userDTO);
    }

    /**
     * Изменение информации о пользователе
     */

    @PostMapping("/me")
    public ResponseEntity<?> updateUser(
            @RequestBody UserUpdateReq userUpdateReq) throws UserNotFoundException {
        userService.updateUser(userUpdateReq);
        return ResponseEntity.ok().body(userUpdateReq);
    }

    /**
     * Обновление аватара пользователя
     */
    @PatchMapping(value ="/me/image",  consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(
            @RequestParam("image") MultipartFile image) throws UserNotFoundException
    {
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }
}
