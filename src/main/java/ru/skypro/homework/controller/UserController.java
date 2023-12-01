package ru.skypro.homework.controller;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import javax.validation.Valid;
import java.io.IOException;

@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    AuthServiceImpl authService;
    @Autowired
    UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(Authentication authentication,
                                         @RequestBody NewPasswordDTO newPassword) {
        if (authService.setPassword(authentication.getName(), newPassword)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.getUserInfo(authentication.getName()));
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO,
                                                    Authentication authentication) {
        return ResponseEntity.ok(userService.updateUser(updateUserDTO, authentication.getName()));
    }


    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestParam MultipartFile image,
                                             Authentication authentication) throws IOException {
        userService.editUserImage(image, authentication.getName());
        return ResponseEntity.ok().build();
    }


}
