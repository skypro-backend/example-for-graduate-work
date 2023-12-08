package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private static final String PHONE_PATTERN = "\\+7\\s?\\(?\\d{3}\\)?\\s?\\d{3}-?\\d{2}-?\\d{2}";
    private static Pattern pattern;
    private static Matcher matcher;


    /**
     * User must be logged in to change password
     *
     * @return response code without body.
     */
    @PostMapping("/set_password")
    public ResponseEntity<?> updatePassword(@RequestBody NewPassword newPassword) {
        if (newPassword.getNewPassword().length() < 8 || newPassword.getNewPassword().length() > 16) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(newPassword);
    }

    /**
     * @return User which You are currently logged in
     */
    @GetMapping("/me")
    public ResponseEntity<User> getMe() {
        return ResponseEntity.ok(new User()); //placeholder user
    }

    /**
     * Checks if updateUser's values are acceptable.
     * @param updateUser - User Update DTO
     * @return status 200 with updateUser as a body if values are correct.
     */

    @PatchMapping("/me")
    public ResponseEntity<?> updateUserInfo(@RequestBody UpdateUser updateUser) {
        if (updateUser.getFirstName().length() < 3 || updateUser.getFirstName().length() > 10) {
            return ResponseEntity.status(403).build();
        }

        if (updateUser.getLastName().length() < 3 || updateUser.getLastName().length() > 10) {
            return ResponseEntity.status(403).build();
        }

        pattern = Pattern.compile(PHONE_PATTERN);
        matcher = pattern.matcher(updateUser.getPhone());

        if (!matcher.matches()) {
            return ResponseEntity.status(403).build();
        }
        return ResponseEntity.ok(updateUser);
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updatePhoto(MultipartFile image) {
        return ResponseEntity.ok().build();
    }

}

