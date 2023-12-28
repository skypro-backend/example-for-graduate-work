package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final UserMapper userMapper;
    private User user;

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
        if (newPasswordDTO.getCurrentPassword() == null) {
            return ResponseEntity.notFound().build();
        }
        userService.setPassword(userMapper.convertToUser(new NewPasswordDTO()));
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public UserDto getMyProfile(@PathVariable("id") Long id) {
        return userMapper.converToUserDto(userService.findById(id));
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateMyProfile(@RequestBody UpdateUserDTO updateUserDTO) {
        if (updateUserDTO == null) {
            return ResponseEntity.notFound().build();
        }
        userService.updateMyProfile(userMapper.convertToUpdateUser(new UpdateUserDTO()));
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/image")
    public ResponseEntity<Void> updateMyImage(@RequestBody ImageDTO imageDTO) {
        if (imageDTO == null) {
            return ResponseEntity.notFound().build();
        }
        userService.setImage(userMapper.convertToImageDTO(user));
        return ResponseEntity.ok().build();
    }

}
