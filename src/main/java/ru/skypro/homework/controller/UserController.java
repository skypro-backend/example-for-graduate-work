package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @PostMapping("/set_password") //+ 401 Unauthorized + 403 Forbidden
    public ResponseEntity<?> updatePassword(@RequestBody NewPassword password) {
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/me") //+ 401 Unauthorized
    public ResponseEntity<User> getInformationByAuthorizedUser(){
        return ResponseEntity.ok(new User());
    }

    @PutMapping("/me") //+ 401 Unauthorized
    public ResponseEntity<UpdateUser> updateInformationFromAuthorizedUser(@RequestBody UpdateUser update){
        return ResponseEntity.ok(new UpdateUser());
    }

    @PutMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE) //+ 401 Unauthorized
    public ResponseEntity<?> updateAvatarFromAuthorizedUser(@RequestBody MultipartFile avatar){
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
