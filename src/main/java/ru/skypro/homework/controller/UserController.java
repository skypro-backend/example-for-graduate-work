package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.repository.UserAvatarRepository;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.UserService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UserController {
    private final UserService userService;
    private final UserAvatarRepository userAvatarRepository;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        UserDto body = userService.getUser();
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUserInfo(@RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(updateUserDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/set_password")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> setNewPassword(Authentication authentication, @RequestBody NewPasswordDto newPasswordDto) {
        String username = authentication.getName();
        userService.updatePassword(newPasswordDto, username);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/me/image")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<Void> updateUserImage(Authentication authentication, @RequestPart MultipartFile image) {
        try {
            userService.saveUserAvatar(authentication, image);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}