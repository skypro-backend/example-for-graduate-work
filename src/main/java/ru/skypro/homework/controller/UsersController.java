package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDto;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserDto;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000")
public class UsersController {
    //    private final UserService userService;

    @GetMapping("/me")
    public UserDto getUser() {
        return new UserDto();
    }

//    @GetMapping("/me")
//    public ResponseEntity<UserDto> getUser() {
//        var body = userService.read();
//        return ResponseEntity.ok(body);
//    }

    @PatchMapping("/me")
    public UpdateUserDto updateUser(@RequestBody UpdateUserDto dto) {
        return new UpdateUserDto();
    }

//    @PatchMapping("/me")
//    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto dto) {
//        userService.update(dto);
//        return ResponseEntity.ok().build();
//    }

    @PatchMapping(path = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public UserDto updateUserImage(@RequestPart(name = "image") MultipartFile image) {
        return new UserDto();
    }


//    @PatchMapping(path = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<?> updateUserImage(@RequestPart(name = "image") MultipartFile image) {
//        userService.update(image);
//        return ResponseEntity.ok().build();
//    }

    @PostMapping("/set_password")
    public NewPasswordDto setPassword(@RequestBody NewPasswordDto dto) {
        return new NewPasswordDto();
    }

//    @PostMapping("/set_password")
//    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDto dto) {
//        userService.update(dto);
//        return ResponseEntity.ok().build();
//    }
}
