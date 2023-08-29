package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {


    private final UserService userService;

    @GetMapping("/user{get}")
    public UserDto getUser(@RequestBody Authentication authentication) {

        return userService.get(authentication);
    }

    @GetMapping("/user{update}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto, Authentication authentication) {
        return ResponseEntity.ok(userService.update(userDto, authentication));
    }

    @GetMapping("/password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        userService.updatePassword(newPassword, authentication);
        return ResponseEntity.ok(newPassword);
    }

    @GetMapping(value = "/avatar")
    public ResponseEntity<String> uploadAvatar(@PathVariable MultipartFile avatar, @RequestParam Authentication authentication) throws IOException {
        userService.updateAvatar(avatar, authentication);
        return ResponseEntity.ok().build();
    }
}
