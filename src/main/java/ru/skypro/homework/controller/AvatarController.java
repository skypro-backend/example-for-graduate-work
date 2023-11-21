package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.service.AvatarService;
import ru.skypro.homework.service.impl.AuthServiceImpl;

import java.io.IOException;

@RestController
@RequestMapping("/avatar")
public class AvatarController {
    AvatarService avatarService;
    AuthServiceImpl authService;

    public AvatarController(AvatarService avatarService, AuthServiceImpl authService) {
        this.avatarService = avatarService;
        this.authService = authService;
    }

    @PatchMapping("/{id}/image")
    public ResponseEntity<String> updateAvatar(@PathVariable("id") Integer id,
                                      @RequestParam MultipartFile image) throws IOException {
        if (authService.getLogin() != null) {
            avatarService.updateAvatar(id, image);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
