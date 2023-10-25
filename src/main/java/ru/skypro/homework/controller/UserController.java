package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.*;
import ru.skypro.homework.security.AdsUserDetails;
import ru.skypro.homework.service.UserService;

import java.io.IOException;


@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    public void updatePassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        userService.updatePassword(newPassword, authentication.getName());
    }

    @GetMapping("/me")
    public User getInformation(Authentication authentication) {
        return userService.getInformation(authentication.getName());
    }

    @PatchMapping("/me")
    public UpdateUser updateInformationAboutUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
        return userService.updateInformationAboutUser(updateUser, authentication.getName());
    }

    @PatchMapping("/me/image")
    public void updateImage(@RequestParam("image") MultipartFile image, Authentication authentication) throws IOException {
        userService.UpdateImage(image, authentication.getName());
    }


}