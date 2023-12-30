package ru.skypro.homework.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UpdateUserDto;
import ru.skypro.homework.service.UserService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequestMapping("http://localhost:8080/updateUser")
public class UpdateUserController {
    private final UserService userService;

    public UpdateUserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/set_info_user")
    public ResponseEntity<UpdateUserDto> setInformationUser(@RequestBody UpdateUserDto updateUser) {
        return ResponseEntity.ok(userService.setInfoUser(updateUser));
    }
}
