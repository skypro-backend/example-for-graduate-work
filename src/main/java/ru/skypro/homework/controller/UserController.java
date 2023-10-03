package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.model_dto.NewPasswordDto;
import ru.skypro.homework.dto.model_dto.UpdateUserDto;
import ru.skypro.homework.dto.model_dto.UserDto;

import javax.annotation.security.RolesAllowed;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password")
    //@RolesAllowed("ADMIN")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser(@RequestBody UserDto userDto) {
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> getUser(@RequestBody UpdateUserDto updateUserDto) {
        return ResponseEntity.ok(updateUserDto);
    }

    @PatchMapping(value = "/me/image", consumes = {"multipart/form-data"})
    public ResponseEntity<UpdateUserDto> getUser(@RequestPart("image") MultipartFile image) {
        return ResponseEntity.ok().build();
    }
}
