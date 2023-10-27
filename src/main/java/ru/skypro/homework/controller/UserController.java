package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
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
    public ResponseEntity<Void> setNewPassword(Authentication authentication,
                                               @RequestBody NewPasswordDto newPasswordDto) {
        String username = authentication.getName();
        userService.updatePassword(newPasswordDto,username);
        return ResponseEntity.ok().build();
    }
    @PostMapping("/me/avatar")
    public ResponseEntity<Void> updateUserAvatar(@RequestPart MultipartFile avatar) {
        try {
            userService.saveUserAvatar(avatar);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Обработка ошибки
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(@RequestPart MultipartFile image) {
        userService.update(image);
        return ResponseEntity.ok().build();
    }
}
