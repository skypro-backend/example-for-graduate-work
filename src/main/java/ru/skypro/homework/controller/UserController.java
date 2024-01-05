package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User.UpdateUser;
import ru.skypro.homework.dto.User.UserDTO;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    @PostMapping("/set_password")
    public ResponseEntity<?> updatePassword(@RequestBody NewPassword password) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getInformationByAuthorizedUser() {
        return ResponseEntity.ok(new UserDTO());
    }

    @PutMapping("/me")
    public ResponseEntity<UpdateUser> updateInformationFromAuthorizedUser(@RequestBody UpdateUser update) {
        return ResponseEntity.ok(new UpdateUser());
    }

    @PutMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateAvatarFromAuthorizedUser(@RequestBody MultipartFile avatar) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
