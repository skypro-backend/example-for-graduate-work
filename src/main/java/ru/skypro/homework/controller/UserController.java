package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final AuthService authService;
    private final ImageService imageService;

    @PostMapping("/set_password")
    public ResponseEntity<Void> updatePassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        authService.updatePassword(newPasswordDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUsers() {
        UserDTO users = userService.getUserInfo();
        return ResponseEntity.ok(users);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateInfoAboutUser(@RequestBody UserDTO userDTO) {
        UserDTO updatedUserInfo = userService.updateInfoUser(userDTO);
        return ResponseEntity.ok(updatedUserInfo);
    }

    @PatchMapping("/me/image")
    public ResponseEntity<byte[]> updateUserImage(@RequestPart MultipartFile image) {
        userService.updateUserImage(image);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/image", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("id") Integer id) {
        byte[]imageData = imageService.getImage(id);
        return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(imageData);
    }
}
