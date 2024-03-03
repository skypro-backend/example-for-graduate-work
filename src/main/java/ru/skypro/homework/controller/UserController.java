package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.user.UpdateUser;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.service.impl.AvatarServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserServiceImpl service;
    private final AvatarServiceImpl avatarService;

    @PostMapping("/set_password")
    @Operation(summary = "Обновление пароля", description = "updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody NewPassword password, Authentication authentication) {
        service.setPassword(new NewPassword(), authentication);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me")
    @Operation(summary = "Получение информации об авторизованном пользователе", description = "getUser")
    public ResponseEntity<UserDTO> getInformationByAuthorizedUser(Authentication authentication) {
        return ResponseEntity.ok(service.getUser(authentication));
    }

    @PatchMapping("/me")
    @Operation(summary = "Обновление информации об авторизованном пользователе", description = "updateUser")
    public ResponseEntity<UpdateUser> updateInformationFromAuthorizedUser(@RequestBody UpdateUser update, Authentication authentication) {
        return ResponseEntity.ok(service.updateUserInfo(update, authentication));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @Operation(summary = "Обновление аватара авторизованного пользователя", description = "updateAvatarUser")
    public ResponseEntity<?> updateAvatarFromAuthorizedUser(@RequestPart MultipartFile image, Authentication authentication) throws IOException {
        service.updateUserAvatar(image, authentication);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE
    })
    @Operation(summary = "Получение аватара пользователя", description = "getImage")
    public ResponseEntity<byte[]> getImage(@PathVariable("id") long id) {
        byte[] image = avatarService.getAvatar(id).getData();
        return ResponseEntity.ok(image);
    }
}
