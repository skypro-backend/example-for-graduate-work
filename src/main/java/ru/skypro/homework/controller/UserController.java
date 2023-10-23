package ru.skypro.homework.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;
import ru.skypro.homework.pojo.User;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.ImageService;

import java.io.IOException;


@RestController
@RequestMapping("/users")
@CrossOrigin ("http://localhost:3000/")
public class UserController {

    private final AuthService authService;

    private final ImageService imageService;

    public UserController(AuthService authService, ImageService imageService) {
        this.authService = authService;
        this.imageService = imageService;
    }


    @GetMapping("/me")
    public UserDTO me(Authentication authentication) {
        UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getDetails();

        UserDTO userDTO = new UserDTO();

        userDTO.setUserName(userDetailsDTO.getUserName());
        userDTO.setPassword(userDetailsDTO.getPassword());

        return userDTO;
    }


    @PostMapping("/set_password")
    public NewPassword setPassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        NewPassword resultPassword = new NewPassword();
        authService.changePassword(
                        authentication.getName(),
                        newPassword.getCurrentPassword(),
                        newPassword.getNewPassword()
                )
                .ifPresent(resultPassword::setCurrentPassword);
        return resultPassword;
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDTO> updateUserInfo(@RequestBody UpdateUserDTO updateUserDTO, Authentication authentication) {
        UserDetailsDTO userDetails = (UserDetailsDTO) authentication.getDetails();
        Long userId = userDetails.getUserId();

        User updatedUser = authService.updateUserInfo(userId, updateUserDTO);

        UserDTO userDTO = new UserDTO();
        userDTO.setFirstName(updatedUser.getFirstName());
        userDTO.setLastName(updatedUser.getLastName());
        userDTO.setPhone(updatedUser.getPhone());

        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/me/image")
    public ResponseEntity<?> updateAvatar(Authentication authentication, @RequestParam("file") MultipartFile file) {
        UserDetailsDTO currentUser = (UserDetailsDTO) authentication.getDetails();

        if (currentUser != null) {
            try {
                imageService.uploadImage(file);
                return ResponseEntity.ok().build();
            } catch (IOException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update avatar");
            }
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authorized");
        }
    }

}
