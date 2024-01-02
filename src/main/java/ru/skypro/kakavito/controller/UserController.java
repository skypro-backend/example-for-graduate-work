package ru.skypro.kakavito.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.kakavito.dto.NewPasswordDTO;
import ru.skypro.kakavito.dto.UpdateUserDTO;
import ru.skypro.kakavito.dto.UserDto;
import ru.skypro.kakavito.exceptions.ImageSizeExceededException;
import ru.skypro.kakavito.model.User;
import ru.skypro.kakavito.service.UserService;

import java.io.IOException;

@Slf4j
@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/set_password")
    public ResponseEntity<String> setNewPassword(@RequestBody NewPasswordDTO newPasswordDTO,
                                                 @NonNull Authentication authentication) {
        if (userService.setNewPassword(authentication.getName(),
                newPasswordDTO.getCurrentPassword(),
                newPasswordDTO.getNewPassword())) {
            return ResponseEntity.ok("Password was update");
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Password don`t match!");
        }
    }

    @GetMapping("/me")
    public ResponseEntity<User> getMyProfile(/*@PathVariable Long id*/) {
        return ResponseEntity.ok(userService.getAuthorizedUser());
//        return ResponseEntity.ok(userMapper.toDTO(userService.getAuthorizedUser()));
//                userService.findById(id);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateMyProfile(@RequestBody UpdateUserDTO updateUserDTO) {
        if (updateUserDTO == null) {
            return ResponseEntity.notFound().build();
        }
        userService.updateMyProfile(updateUserDTO);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/me/image",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<Void> updateMyImage(@RequestBody MultipartFile image) throws IOException, ImageSizeExceededException {
        userService.updateMyImage(image);
        return ResponseEntity.ok().build();
    }

}
