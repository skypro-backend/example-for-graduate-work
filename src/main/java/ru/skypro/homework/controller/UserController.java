package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.user.*;
import ru.skypro.homework.service.UserService;

import javax.validation.Valid;

/**
 * The class-controller for running user's endpoints
 * @author Sulaeva Marina
 */
@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * The method for updating password for registered users with checking input data
     */
    @PostMapping("/set_password")
    public void updatePassword(@RequestBody @Valid NewPassword newPassword, Authentication authentication) {
        userService.updatePassword(newPassword, authentication.getName());
    }
    /**
     * The method for getting information about profile for registered users
     */
    @GetMapping("/me")
    public User getInformation(Authentication authentication) {
        return userService.getInformation(authentication.getName());
    }

    /**
     * The method for updating information (first name, last name, phone) for registered users
     */
    @PatchMapping("/me")
    public UpdateUser updateInformationAboutUser(@RequestBody @Valid UpdateUser updateUser, Authentication authentication) {
        return userService.updateInformationAboutUser(updateUser, authentication.getName());
    }

    /**
     * The method for updating image in profile for registered users
     */
    @PatchMapping("/me/image")
    public ResponseEntity<byte []> updateImage(@RequestPart MultipartFile image, Authentication authentication) {
        userService.updateImage(image, authentication.getName());
        return ResponseEntity.ok().build();
    }
    /**
     * The method to output on display image in profile for registered users
     */
    @GetMapping(value ="/{id}/image", produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE, "image/*"})
    public byte [] getImage(@PathVariable("id") String id) {
        return userService.getImage(id);
    }



}