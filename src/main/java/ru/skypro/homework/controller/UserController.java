package ru.skypro.homework.controller;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

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
    public UserDto getMyProfile(@PathVariable("id") Long id) {
        return userService.findById(id);
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
    public ResponseEntity<byte[]> updateMyImage(@RequestBody MultipartFile image) throws IOException {
        byte[] updatedAvatar = userService.updateMyImage(image);
        if (updatedAvatar.length != 0) {
            return ResponseEntity.ok().body(updatedAvatar);
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

}
