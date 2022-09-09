package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.models.dto.CreateUserDto;
import ru.skypro.homework.models.dto.NewPasswordDto;
import ru.skypro.homework.models.dto.ResponseWrapper;
import ru.skypro.homework.models.dto.UserDto;
import ru.skypro.homework.service.UserService;

import java.util.List;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<CreateUserDto> addUser(@RequestBody CreateUserDto user) {
        CreateUserDto result = userService.addUser(user);
        // if ... exception
        return ResponseEntity.ok(result);
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapper<UserDto>> getUsers() {
        List<UserDto> list = userService.getUsers();
        // if ... exception
        return ResponseEntity.ok(new ResponseWrapper(list));
    }

    @PatchMapping("me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto user) {
        UserDto result = userService.updateUser(user);
        // if ... exception
        return ResponseEntity.ok(result);
    }

    @PostMapping("set_password")
    public ResponseEntity<NewPasswordDto> setPassword(@RequestBody NewPasswordDto newPassword) {
        NewPasswordDto result = userService.setPassword(newPassword);
        // if ... exception
        return ResponseEntity.ok(result);
    }

    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable Integer id) {
        UserDto result = userService.getUser(id);
        // if ... exception
        return ResponseEntity.ok(result);
    }



}
