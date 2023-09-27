package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPasswordDTO;
import ru.skypro.homework.dto.UpdateUserDTO;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.service.impl.UserServiceImpl;
import ru.skypro.homework.store.entities.UserEntity;
import ru.skypro.homework.mappers.UserMapper;
import ru.skypro.homework.store.repositories.UserRepository;

import java.util.Optional;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@RestController
@RequestMapping("/users")
public class UserController {

    UserServiceImpl userService;

    UserRepository userRepository;

    @Operation(summary = "Обновление пароля")
    @PostMapping("/set_password")
    public ResponseEntity<NewPasswordDTO> updatePassword(@RequestBody NewPasswordDTO user) {

        NewPasswordDTO newPasswordDTO = null;

        return ResponseEntity.ok().body(newPasswordDTO);
    }

    @Operation(summary = "Получение информации об авторизованном пользователе")
    @GetMapping("/me")
    public ResponseEntity<?> getUser(@RequestBody UserDTO user) {

        return ResponseEntity.ok().body(null);
    }

    @Operation(summary = "Обновление информации об авторизованном пользователе")
    @PatchMapping("/me")
    public ResponseEntity<UpdateUserDTO> updateUser(@RequestBody UpdateUserDTO user) {

        Optional<UserEntity> byId = userRepository.findById(1);
        UserEntity userTest = byId.orElse(null);

        UpdateUserDTO userDTO1 = userService.updateUser(userTest);
        log.info("User updated successfully");
        UpdateUserDTO updateUserDTO = null;
        return ResponseEntity.ok().body(updateUserDTO);

    }

    @Operation(summary = "Обновление аватара авторизованного пользователя")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateUserAvatar(@RequestPart MultipartFile image) {

        return ResponseEntity.ok().build();
    }
}
