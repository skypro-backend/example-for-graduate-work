package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    //здесь будет зависимость от UserService

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword) {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(new User(), HttpStatus.OK);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> patchUser(@RequestBody UpdateUser updateUser) {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(new UpdateUser(), HttpStatus.OK);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> patchUserAvatar(@RequestParam("image") MultipartFile file) {
        //здесь будет вызов сервиса
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
