package ru.skypro.homework.controller.users;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.auth.NewPasswordDto;
import ru.skypro.homework.dto.users.UpdateUserDto;
import ru.skypro.homework.dto.users.UserDto;
import ru.skypro.homework.service.image.ImageService;
import ru.skypro.homework.service.users.impl.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Tag(name = "Пользователи")
@RestController
@CrossOrigin(value = "http://localhost:3000")
@Slf4j
@Validated
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final ImageService imageService;

    public UserController(UserService userService, ImageService imageService) {
        this.userService = userService;
        this.imageService = imageService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDto newPasswordDto) {
        userService.setPassword(newPasswordDto);
        log.info("Новый пароль установлен");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> getUser() {
        UserDto currentUserDto = userService.getUser();
        if (currentUserDto == null) {
            return ResponseEntity.notFound().build();
        }
        log.info("Данные о пользователе получены");
        return ResponseEntity.ok(currentUserDto);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateUser(@RequestBody UpdateUserDto updateUserDto) {
        UpdateUserDto newUser = userService.updateUser(updateUserDto);
        log.info("Новый пользователь создан или данные о пользователе обновлены");
        return ResponseEntity.ok(newUser);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserImage(@RequestPart MultipartFile image) throws IOException {
        userService.updateUserImage(image);
        log.info("Аватар успешно обновлен {}", image);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/me/images", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<byte[]> getUserImage() throws IOException {
        log.info("Получение фотографии пользователя");

        byte[] imageBytes = userService.getUserImage();

        UserDto userDto = userService.getUser();
        String urlToImage = userDto.getImage();
        Path fullPathToImageOfAvatars = imageService.getFullPathToImageOfAvatars(urlToImage);

        long fileSize = Files.size(fullPathToImageOfAvatars);
        String mediaType = Files.probeContentType(fullPathToImageOfAvatars);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.parseMediaType(mediaType));
        httpHeaders.setContentLength(fileSize);

        return new ResponseEntity<>(imageBytes, httpHeaders, HttpStatus.OK);
    }

}
