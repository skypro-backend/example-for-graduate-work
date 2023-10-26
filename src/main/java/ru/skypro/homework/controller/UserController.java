package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo() {

        var body = userService.getUser();
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUserInfo(@RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(updateUserDto);
        return ResponseEntity.ok().build();
    }
    @PatchMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        userService.updatePassword(newPasswordDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestPart MultipartFile image) {
        userService.update(image);
        return ResponseEntity.ok().build();
    }
}
