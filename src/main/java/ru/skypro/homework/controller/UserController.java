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
import ru.skypro.homework.dto.*;
import ru.skypro.homework.service.impl.AvatarServiceImpl;
import ru.skypro.homework.service.impl.UserServiceImpl;

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
    public ResponseEntity<?> updatePassword(@RequestBody NewPassword newPassword, Authentication authentication) {
        service.setPassword(newPassword,authentication);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me") //+ 401 Unauthorized
    @Operation(summary = "Получение информации об авторизованном пользователе", description = "getUser")
    public ResponseEntity<UserDto> getInformationByAuthorizedUser(Authentication authentication){
        return ResponseEntity.ok(service.getUser(authentication));
    }

    @PatchMapping("/me") //+ 401 Unauthorized
    @Operation(summary = "Обновление информации об авторизованном пользователе", description = "updateUser")
    public ResponseEntity<UpdateUser> updateInformationFromAuthorizedUser(@RequestBody UpdateUser update, Authentication authentication){
        return ResponseEntity.ok(service.updateUserInfo(update,authentication));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //+ 401 Unauthorized
    @Operation(summary = "Обновление аватара авторизованного пользователя", description = "updateAvatarUser")
    public ResponseEntity<?> updateAvatarFromAuthorizedUser(@RequestPart MultipartFile image, Authentication authentication){
        service .updateUserAvatar(image,authentication);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Получение аватара пользователя", description = "getImage")
    @GetMapping(value = "/image/{id}", produces = {MediaType.IMAGE_PNG_VALUE,
            MediaType.IMAGE_JPEG_VALUE,
            MediaType.IMAGE_GIF_VALUE})
    public ResponseEntity<byte[]> getImage(@PathVariable long id) {
        return ResponseEntity.ok(avatarService.getAvatar(id).getData());
    }
}
