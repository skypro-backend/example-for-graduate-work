package ru.skypro.homework.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/setPassword")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword userService) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/getUser")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(new User(),HttpStatus.OK);
    }
    @PutMapping("/updateUser")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody  UserService userService) {
        return new ResponseEntity<>(new UpdateUser(), HttpStatus.OK);
    }
    @PutMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage( @RequestParam MultipartFile image) {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
