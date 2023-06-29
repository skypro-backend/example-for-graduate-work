package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.LoginReq;
import ru.skypro.homework.dto.PasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UsersDTO;

@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {


    @PostMapping("/set_password")
    public ResponseEntity<Void> setNewPassword(@RequestBody LoginReq req,
                                         @RequestBody PasswordDTO passwordDTO) {
        if (passwordDTO.getCurrentPassword().equals(req.getPassword())) {
            req.setPassword(passwordDTO.getNewPassword());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }


    @GetMapping("/me")
    public ResponseEntity<UsersDTO> getUser(@RequestParam Integer id) {
        return ResponseEntity.ok(new UsersDTO());
    }


    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO updateUserDTO) {
        return ResponseEntity.ok().build();
    }


    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> updateUserImage(@RequestParam MultipartFile file) {
        return ResponseEntity.ok().build();
    }
}
