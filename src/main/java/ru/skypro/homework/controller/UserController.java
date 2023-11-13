package ru.skypro.homework.controller;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

import java.io.IOException;

@RestController
@RequestMapping("users")
@CrossOrigin(value = "http://localhost:3000")
public class UserController {

    public UserController() {
    }

    @PostMapping("/set_password") // POST http://localhost:8080/users/set_password
    public ResponseEntity<String> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me") // GET http://localhost:8080/users/me
    public ResponseEntity<UserDto> getUser() {
        UserDto userDto = new UserDto();
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/me") // PATCH http://localhost:8080/users/me
    public ResponseEntity<String> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok().build();
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // PATCH http://localhost:8080/users/me/image
    public ResponseEntity<String> updateAvatar(@RequestParam MultipartFile image) throws IOException {
        return ResponseEntity.ok().build();
    }

}
