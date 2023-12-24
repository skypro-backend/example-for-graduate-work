package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.UserService;

import java.io.IOException;
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
public class AvatarController {
    private final UserService userService;

    public AvatarController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> changeAvatar(@RequestBody MultipartFile avatar) throws IOException {
        userService.setAvatar(avatar);
        return ResponseEntity.ok().build();
    }
}
