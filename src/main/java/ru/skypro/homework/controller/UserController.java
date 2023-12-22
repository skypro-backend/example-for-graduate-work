package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //Получение информации о пользователе

    @GetMapping("/me")
    public ResponseEntity<UserDTO>getUser() {
        return ResponseEntity.ok(userService.getCurrentUser());
    }

    //Обновление информации о пользователе

    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        if (null==updateUserDTO) {
            return ResponseEntity.noContent().build();
        }
        UserDTO editUser = userService.updateUser(updateUserDTO);
        if (null==editUser) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(editUser);
    }

    //Смена пароля у пользователя
    @PostMapping("/set_password")
    public ResponseEntity<?> setUserPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        return ResponseEntity.ok(userService.setPassword(newPasswordDTO));
    }


    //Обновление аватара у пользователя
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> loadImageByUser(@RequestPart(value = "image") MultipartFile image) {
        userService.updateUserImage(image, SecurityContextHolder.getContext().getAuthentication().getName());
        return ResponseEntity.ok().build();

    }
}
