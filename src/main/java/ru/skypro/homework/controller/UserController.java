package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

//    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDTO newPasswordDTO) {
//        if (user.getPassword == null) {
//            return ResponseEntity.notFound().build();
//        }
//        userService.add(user.setPassword);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/me")
    public ResponseEntity<?> getMyProfile(@RequestBody UserDto userDto) {
//        User curantUser = userService.findById(id = this.id);
//        if (curantUser == null) {
//            return ResponseEntity.notFound().build();
//        }
        return ResponseEntity.ok(userDto);
    }

    @PatchMapping("/me")
    public ResponseEntity<?> updateMyProfile(@RequestBody UpdateUserDTO updateUserDTO) {
//        if (user == null) {
//            return ResponseEntity.notFound().build();
//        }
//        user.setFirstName();
//        user.setLastName();
//        user.setPhone();
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/me/image")
    public ResponseEntity<?> updateMyAvatar(@RequestBody ImageDTO imageDTO) {
//        if (user.getImage == null) {
//            return ResponseEntity.notFound().build();
//        }
//        user.setImage();
        return ResponseEntity.ok().build();
    }

//    private User convertToUser(UserDto userDto) {
//        return modelMapper.map(userDto, User.class);
//    }
}
