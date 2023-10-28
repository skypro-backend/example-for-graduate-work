package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.projections.NewPassword;
import ru.skypro.homework.projections.UpdateUser;
import ru.skypro.homework.repository.UserRepo;
import ru.skypro.homework.service.impl.UserServiceImpl;

import javax.validation.Valid;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserServiceImpl userService;
    private final UserRepo userRepo; // так... потестить

    // Получение пользователя
    @GetMapping("/me")
    public UserDTO getUser(Authentication authentication) {
//        return ResponseEntity.ok(new UserDTO(0, "fe@mail.ru", "nameForTest", "LastnameForTest", "+79999999999", Role.USER.name(), null));
        return userService.getUser(authentication);
    }


    @PostMapping("/set_password")
    public void setPassword(@RequestBody @Valid NewPassword newPassword) {
        userService.updatePassword(newPassword);
    }

    @PatchMapping("/me")
    public UpdateUser updateUser(@RequestBody @Valid UpdateUser updateUser, Authentication authentication) {
        return userService.updateUser(updateUser, authentication);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestPart("image") MultipartFile newImage) {
        return ResponseEntity.ok("OK");
    }
}
