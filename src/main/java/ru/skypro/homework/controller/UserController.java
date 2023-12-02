package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> get() {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(new UserDTO());
    }

    @PatchMapping("/users/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUserDto) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new UpdateUser());
    }

    @PatchMapping("/users/me/image")
    public ResponseEntity<String> updateUserImage(@RequestBody MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(new ImageDTO().getLink());
    }
    @PutMapping("/users/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPasswordDto) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new NewPassword());
    }

}
