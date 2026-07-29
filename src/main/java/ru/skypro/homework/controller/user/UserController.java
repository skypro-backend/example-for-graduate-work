package ru.skypro.homework.controller.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.data.dto.user.SetPasswordRequest;
import ru.skypro.homework.data.dto.user.UpdateUserProfileRequest;
import ru.skypro.homework.data.dto.user.UserDto;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe() {
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUserInfo(
            @RequestBody UpdateUserProfileRequest updateRequest
    ) {
        return ResponseEntity.ok(null);
    }

    @PostMapping("/me/image")
    public ResponseEntity<UserDto> updateUserAvatar(
            MultipartFile file
    ) {
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/set_password")
    public ResponseEntity<UserDto> updatePassword(
            @RequestBody SetPasswordRequest setPasswordRequest
    ) {
        return ResponseEntity.ok(null);
    }


}
