package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.user.NewPassword;
import ru.skypro.homework.dto.user.UpdateImage;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.User;
import ru.skypro.homework.service.UserService;


@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    public void updatePassword(@RequestBody NewPassword newPassword) {
        userService.updatePassword(newPassword);
    }

    @GetMapping("/me")
    public User getInformation() {
        return userService.getInformation();
    }

    @PatchMapping("/me")
    public UpdateUser updateInformationAboutUser(@RequestBody UpdateUser updateUser) {
        return userService.updateInformationAboutUser(updateUser);
    }

    @PatchMapping("/me/image")
    public void updateImage(UpdateImage image) {
        userService.UpdateImage(image.getNewImage());
    }


}