package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;



import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final UserService userService;


    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        UserDto body = userService.getUser(username);
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUserInfo(@RequestBody UpdateUserDto updateUserDto, Authentication authentication) {
        String username = authentication.getName();
        if (userService.isUserAllowedToUpdate(username, updateUserDto)) {
            userService.updateUser(username, updateUserDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @PostMapping("/set_password")
    public ResponseEntity<Void> setNewPassword(Authentication authentication, @RequestBody NewPasswordDto newPasswordDto) {
        String username = authentication.getName();
        if (userService.isUserAllowedToSetPassword(username)) {
            userService.updatePassword(username, newPasswordDto);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("/me/avatar")
    public ResponseEntity<?> saveUserAvatar(@RequestPart("image") MultipartFile image, Authentication authentication) {
        userService.saveUserAvatar(authentication, image);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> updateUserImage(@RequestPart("image") MultipartFile image, Authentication authentication) {
        String username = authentication.getName();
        userService.updateUserImage(image, username);
        return ResponseEntity.ok().build();
    }


    @Value("${image.store.path}")
    private String storePath;

    @GetMapping(value = "/images/{file.png}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getAvatar(@PathVariable("file.png") String file, Authentication authentication) {
        byte[] avatarData = userService.getAvatarImage(file);

        if (avatarData != null) {
            return ResponseEntity.ok(avatarData);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}