package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserMapper userMapper;

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(
            @RequestBody NewPassword dto
    ) {
        userService.updatePassword(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<UserDTO> getMe() {
        return ResponseEntity.ok(userMapper.toUserDTO(userService.getMe()));
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateMe(
            @RequestBody UpdateUser dto
    ) {
        return ResponseEntity.ok(userMapper.toUpdateUser(userService.updateMe(dto)));
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateMyImage(
            @RequestPart("image") MultipartFile image
    ) {
        userService.updateMyImage(image);
        return ResponseEntity.ok().build();
    }
}