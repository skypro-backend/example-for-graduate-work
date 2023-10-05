package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UsersController {

    /**
     * Метод для обновления пароля
     * @param newPassword принимает новый пароль от пользователя
     * @return статус 200, если новый пароль не совпадает с текущим паролем и сохранился в БД
     */
    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword) {
        //если новый пароль совпадает с текущим паролем, то вернуть статус ошибки 403(типо бесполезно поворять с тем же запросом)
        //если новый пароль не совпадает с текущим паролем и записал в БД, то вернуть статус 200
        //return ResponseEntity.ok().build();
        return null;
    }

    /**
     * Метод для получения всей информации о пользователе
     * @return всю информацию о пользователе из БД
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        //если нет ни одного пользователя, вернуть статус ошибки 401(типо в следующий раз в БД может быть пользователь)
        //если в БД таблица не пуста, вернуть статус 200
        //return ResponseEntity.ok().build();
        return null;
    }

    /**
     * Метод для обновления информации о пользователе
     * @return статус 200, если данные успешно обновлены
     */
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser() {
        //если такого пользователя не существует, то вернуть 401(типо в следующий раз в БД может быть пользователь)
        //если такой пользователь существует, сохранить изменения, и вернуть статус 200
        //return ResponseEntity.ok().build();
        return null;
    }

    /**
     * Метод для обновления аватарки пользователя
     * @param file принимает файл аватарки пользователя
     * @return статус 200, если аватарка успешно обновлена
     */
    @PatchMapping("/me/image")
    public ResponseEntity<User> updateUserImage(@RequestParam("image") MultipartFile file) {
        //если такой аватарки пользователя не существует, то вернуть 401(типо в следующий раз в БД может быть аватарка пользователя)
        //если такая аватарка пользователя существует, вернуть статус 200
        //return ResponseEntity.ok().build();
        return null;
    }
}
