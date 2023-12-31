package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;


@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UsersController {
    @PostMapping("/users/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        return ResponseEntity.ok().build();
    }

    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(new UserDTO());
    }

    @PatchMapping("/users/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        return ResponseEntity.ok(updateUserDTO);
    }

    @PatchMapping(value = "/users/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage(@RequestPart("image") MultipartFile multipartFile) {
        return ResponseEntity.ok().build();
    }
}
