package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDto;

import javax.transaction.Transactional;

@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/users")
public class UserController {

    @Operation(summary = "Обновление пароля")
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDTO> updatePassword(@RequestBody NewPasswordDTO user) {

        NewPasswordDTO newPasswordDTO = new NewPasswordDTO();

        return ResponseEntity.ok().body(newPasswordDTO);
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @GetMapping("/me")
    public ResponseEntity<?> getUser(@RequestBody UserDto user) {

        UserDto userDto = new UserDto();

        return ResponseEntity.ok().body(userDto);
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO user) {

        UpdateUserDTO updateUserDTO = new UpdateUserDTO();

        return ResponseEntity.ok().body(updateUserDTO);
    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserAvatar(@RequestPart MultipartFile image) {

        return ResponseEntity.ok().build();
    }
}
