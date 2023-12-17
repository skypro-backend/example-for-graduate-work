package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.impl.ImageServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000/")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/set_password")
    public void setPassword(@RequestBody NewPassword newPassword,
                            @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        userService.setPassword(newPassword, userDetails);
    }

    @GetMapping("/me")
    public User getUser(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUser(userDetails);
    }

    @PatchMapping("/me")
    public UpdateUser updateUser(@RequestBody UpdateUser updateUser,
                                 @AuthenticationPrincipal UserDetails userDetails) {
        return userService.updateUser(updateUser, userDetails);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void updateImage(@RequestParam("image") MultipartFile image,
                            @AuthenticationPrincipal UserDetails userDetails) {
        userService.updateImage(image, userDetails);
    }
}