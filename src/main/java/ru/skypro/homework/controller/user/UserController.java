package ru.skypro.homework.controller.user;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.data.dto.user.SetPasswordRequest;
import ru.skypro.homework.data.dto.user.UpdateUserProfileRequest;
import ru.skypro.homework.data.dto.user.UserDto;
import ru.skypro.homework.service.AccountService;
import ru.skypro.homework.service.ProfileService;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final ProfileService profileService;
    private final AccountService accountService;

    @GetMapping("/me")
    public ResponseEntity<UserDto> getMe(
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                profileService.getUserProfile(authentication.getName()));
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserProfileRequest> updateUserInfo(
            @RequestBody UpdateUserProfileRequest updateRequest,
            Authentication authentication
    ) {
        return ResponseEntity.ok(
                profileService.updateUserProfile(authentication.getName(), updateRequest)
        );
    }

    @PostMapping("/me/image")
    public ResponseEntity<UserDto> updateUserAvatar(
            MultipartFile file
    ) {
        // TODO("realize in stage 4")
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/set_password")
    public void updatePassword(
            @RequestBody SetPasswordRequest setPasswordRequest,
            Authentication authentication
    ) {
        accountService.setPassword(authentication.getName(), setPasswordRequest);
    }


}
