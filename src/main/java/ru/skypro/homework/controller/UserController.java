package ru.skypro.homework.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.dto.UserUpdate;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
import java.util.Optional;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @PostMapping("set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword) {

        if (authService.changePassword(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), newPassword)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        }

        return ResponseEntity.status(HttpStatus.FORBIDDEN).build();

    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {

        Optional<UserDto> userOptional = userService.getUserByDtoByLogin(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName());

        return userOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());

    }

    @PatchMapping("/me")
    public ResponseEntity<UserUpdate> updateUser(@RequestBody UserUpdate userUpdate) {

        Optional<UserUpdate> userUpdateOptional = userService.updateUserByUserUpdate(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), userUpdate);

        return userUpdateOptional.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NO_CONTENT).build());

    }

    @PatchMapping(value = "me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestPart MultipartFile image) throws IOException {

        if(userService.changeImage(SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName(), image).isPresent()){
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }

}
