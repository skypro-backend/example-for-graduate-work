package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        if (userService.setPassword(newPasswordDto)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        return ResponseEntity.ok(userService.getUser());
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(userService.updateUser(updateUserDto));
    }

    @PatchMapping(path = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestPart("image") MultipartFile multipartFile) {
        userService.updateUserImage(multipartFile);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "/{id}/image")
    public ResponseEntity<byte[]> getUserImage(@PathVariable Integer id) {
        return ResponseEntity.ok()
                .body(userService.getImage(id));
    }
}
