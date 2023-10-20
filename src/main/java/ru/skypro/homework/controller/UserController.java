package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.*;
import ru.skypro.homework.service.UserService;


@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    public void updatePassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        AdsUserDetails adsUserDetails = (AdsUserDetails) authentication.getPrincipal();
        userService.updatePassword(newPassword, adsUserDetails.getId());
    }

    @GetMapping("/me")
    public User getInformation(Authentication authentication) {
        AdsUserDetails adsUserDetails = (AdsUserDetails) authentication.getPrincipal();
        return userService.getInformation(adsUserDetails.getId());
    }

    @PatchMapping("/me")
    public UpdateUser updateInformationAboutUser(@RequestBody UpdateUser updateUser, Authentication authentication) {
        AdsUserDetails adsUserDetails = (AdsUserDetails) authentication.getPrincipal();
        return userService.updateInformationAboutUser(updateUser, adsUserDetails.getId());
    }

    @PatchMapping("/me/image")
    public void updateImage(@RequestParam("image") MultipartFile image, Authentication authentication) {
        AdsUserDetails adsUserDetails = (AdsUserDetails) authentication.getPrincipal();
        userService.UpdateImage(image, adsUserDetails.getId());
    }


}