package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.repository.UserAvatarRepository;
import ru.skypro.homework.repository.UserRepository;
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
    private final UserAvatarRepository userAvatarRepository;
    private final UserRepository userRepository;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUserInfo(Authentication authentication) {
        String username = authentication.getName();
        UserDto body = userService.getUser();
        return ResponseEntity.ok(body);
    }

    @PatchMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UpdateUserDto> updateUserInfo(@RequestBody UpdateUserDto updateUserDto) {
        userService.updateUser(updateUserDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/set_password")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Void> setNewPassword(Authentication authentication, @RequestBody NewPasswordDto newPasswordDto) {
        String username = authentication.getName();
        userService.updatePassword(newPasswordDto, username);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> updateUserImage(@RequestPart("image") MultipartFile image,
                                             Authentication authentication) throws IOException {
        userService.update(image);
        return ResponseEntity.ok().build();
    }

    @Value("${image.store.path}")
    private String storePath;

    @GetMapping(value = "/images/{file.png}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getAvatar(@PathVariable("file.png") String file, Authentication authentication) {
        Path path = Paths.get(storePath, file).toAbsolutePath().normalize();
        try {
            byte[] avatarBytes = Files.readAllBytes(path);
            return ResponseEntity.ok(avatarBytes);
        } catch (IOException e) {
            throw new RuntimeException("Не удалось получить аватар", e);
        }
    }
}
