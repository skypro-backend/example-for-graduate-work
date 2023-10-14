package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UsersService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UsersService usersService;

    /**
     * Метод для обновления пароля.
     *
     * @param newPassword принимает новый пароль от пользователя.
     * @return статус 200, если новый пароль не совпадает с текущим паролем и сохранился в БД.
     */
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        if (usersService.setPassword(newPassword)) {
            //если новый пароль не совпадает с текущим паролем и записал в БД, то вернуть статус 200
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        //если новый пароль совпадает с текущим паролем, то вернуть статус ошибки 403(типо бесполезно поворять с тем же запросом)
        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
    }

    /**
     * Метод для получения всей информации об авторизованном пользователе.
     *
     * @return всю информацию о пользователе из БД.
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return ResponseEntity.status(HttpStatus.OK).body(usersService.getUser());
    }

    /**
     * Метод для обновления информации о пользователе.
     *
     * @param updateUser принимает новые данные пользователя.
     * @return обновлённые данные пользователя.
     */
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        if (usersService.updateUser(updateUser)) {
            return ResponseEntity.status(HttpStatus.OK).body(updateUser);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    /**
     * Метод для обновления аватарки пользователя.
     *
     * @param file принимает файл аватарки пользователя.
     * @return статус 200, если аватарка успешно обновлена.
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile file) throws IOException {
        if (usersService.updateUserImage(file)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
