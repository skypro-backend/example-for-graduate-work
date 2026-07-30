package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import javax.validation.Valid;

/**
 * REST-контроллер профиля авторизованного пользователя.
 */
@RestController
@RequestMapping("/users")
@Validated
public class UserController {

    private final UserService userService;

    public UserController(
            UserService userService
    ) {
        this.userService = userService;
    }

    /**
     * Возвращает профиль текущего пользователя.
     *
     * @param authentication данные авторизации
     * @return профиль пользователя
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUser(
            Authentication authentication
    ) {
        User user = userService.getUser(
                authentication.getName()
        );

        return ResponseEntity.ok(user);
    }

    /**
     * Обновляет имя, фамилию и телефон пользователя.
     *
     * @param updateUser    новые данные профиля
     * @param authentication данные авторизации
     * @return обновлённые данные
     */
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(
            @Valid @RequestBody UpdateUser updateUser,
            Authentication authentication
    ) {
        UpdateUser result = userService.updateUser(
                authentication.getName(),
                updateUser
        );

        return ResponseEntity.ok(result);
    }

    /**
     * Изменяет пароль пользователя.
     *
     * @param newPassword   текущий и новый пароли
     * @param authentication данные авторизации
     * @return 200 при успешном изменении;
     * 403 при неверном текущем пароле
     */
    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(
            @Valid @RequestBody NewPassword newPassword,
            Authentication authentication
    ) {
        boolean changed = userService.setPassword(
                authentication.getName(),
                newPassword
        );

        if (!changed) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .build();
        }

        return ResponseEntity.ok().build();
    }

    /**
     * Обновляет аватар текущего пользователя.
     *
     * @param image файл изображения
     * @param authentication данные авторизации
     * @return ответ без тела
     * @throws IOException если файл невозможно прочитать
     */
    @PatchMapping(
            value = "/me/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
    )
    public ResponseEntity<Void> updateUserImage(
            @RequestPart("image") MultipartFile image,
            Authentication authentication
    ) throws IOException {

        if (image.isEmpty()) {
            return ResponseEntity
                    .badRequest()
                    .build();
        }

        String mediaType = image.getContentType();

        if (mediaType == null
                || !mediaType.startsWith("image/")) {

            return ResponseEntity
                    .badRequest()
                    .build();
        }

        userService.updateImage(
                authentication.getName(),
                image.getBytes(),
                mediaType
        );

        return ResponseEntity.ok().build();
    }

    /**
     * Возвращает аватар пользователя.
     *
     * @param userId идентификатор пользователя
     * @return файл изображения
     */
    @GetMapping("/{userId}/image")
    public ResponseEntity<byte[]> getUserImage(
            @PathVariable Long userId
    ) {
        byte[] image = userService.getImage(userId);

        String mediaType =
                userService.getImageMediaType(userId);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_TYPE,
                        mediaType
                )
                .body(image);
    }
}
