package ru.skypro.homework.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UsersService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UsersController {

    private final UsersService usersService;

    // TODO Обновление пароля
    @PostMapping("/set_password")
    ResponseEntity<?> setPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        return ResponseEntity.ok().build();
    }

    // TODO Получить информацию об авторизованном пользователе
    @GetMapping("/me")
    ResponseEntity<UserDTO> getAuthorisedUserInfo() {

        return ResponseEntity.ok(usersService.getAuthorisedUser());
    }

    // TODO Обновить информацию об авторизованном пользователе
    @PatchMapping("/me")
    ResponseEntity<UserDTO> setAuthorisedUserInfo(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(usersService.updateUser(userDTO));
    }

    // TODO Обновить аватар авторизованного пользователя
    @PatchMapping(value = "/me/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    ResponseEntity<?> setAuthorisedUserAvatar(@RequestPart("image") MultipartFile image) {
        usersService.setAvatar(1L, image);
        return ResponseEntity.ok().build();
    }
}
