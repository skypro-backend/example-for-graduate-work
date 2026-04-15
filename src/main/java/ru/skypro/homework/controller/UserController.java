package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPassword) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        UserDto user = new UserDto();
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUser) {
        UpdateUserDto updateUser1 = new UpdateUserDto();
        return ResponseEntity.ok(updateUser1);
    }

    @PatchMapping(value = "/me/image", consumes = "multipart/form-data")
    public ResponseEntity<Void> updateUserImage(@RequestParam("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
