package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UsersService;

import javax.validation.Valid;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@Validated
@RequestMapping("/users")
@RequiredArgsConstructor
public class UsersController {

    private final UsersService usersService;

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@Valid @RequestBody NewPassword newPassword) {
        usersService.setPassword(newPassword.getCurrentPassword(), newPassword.getNewPassword());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        User user = usersService.getUser();
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> patchUser(@Valid @RequestBody UpdateUser updateUser) {
        UpdateUser updatedUser = usersService.updateUser(updateUser);
        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> patchUserAvatar(@RequestParam("image") MultipartFile file) {
        usersService.updateUserImage(file);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
