package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.exception.UserNotFoundException;
import ru.skypro.homework.service.AuthService;
import ru.skypro.homework.service.UserService;
import ru.skypro.homework.service.impl.UserServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;
    private final AuthService authService;

    //!!!!Доработать внутренность

    @PostMapping("/set_password")
    public NewPassword setPassword(
            @RequestBody NewPassword newPassword)
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authService.changePassword(authentication.getName(), newPassword.getCurrentPassword(),
                                   newPassword.getNewPassword());
        return newPassword;
    }

    //!!!!Доработать внутренность
    @GetMapping("/me")
    public ResponseEntity<UserDTO> getUser() {
        return ResponseEntity.ok(new UserDTO());
    }

    //!!!!Доработать внутренность
    @PostMapping("/me")
    public ResponseEntity<Void> updateUser(
            @RequestBody UserDTO user)
    {
        userService.updateUser(user);
        return ResponseEntity.ok().build();
    }

    //!!!!Доработать внутренность
    @PostMapping("/me/image")
    public ResponseEntity<Void> updateUserImage(
            @RequestBody MultipartFile image)
    {
        return ResponseEntity.ok().build();
    }
}
