package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassDto;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.dto.UserInfoDto;

@RestController
@RequestMapping("/users")
@CrossOrigin(value = "http://localhost:3000")
public class UsersController {


    @PostMapping("/set_password")
    public ResponseEntity<NewPassDto> updatePassword(
            @RequestBody NewPassDto newPassDto) {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/me")
    public ResponseEntity<UserInfoDto> getInfoAboutUser() {
        return new ResponseEntity<>(HttpStatus.OK);//изменить на DTO
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDto> updateInfoAboutUser(
            @RequestBody UpdateUserDto updateUserDto) {
        return new ResponseEntity<>(HttpStatus.OK); //изменить на DTO
    }

    @PatchMapping("/me/image")
    ResponseEntity<byte[]> updateUserImage(
            @RequestPart MultipartFile image) {
        return null;
    }
}
