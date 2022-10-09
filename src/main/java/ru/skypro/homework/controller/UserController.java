package ru.skypro.homework.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.ResponseWrapperUser;
import ru.skypro.homework.dto.UserDto;
import ru.skypro.homework.service.UserService;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<CreateUser> addUser(@RequestBody CreateUser createUser) {
        if (createUser == null) {
            return ResponseEntity.notFound().build();
        }
        UserDto userDto = userService.addUser(createUser);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        return ResponseEntity.ok(createUser);
    }

    @GetMapping("/me")
    public ResponseEntity<ResponseWrapperUser> getUsers() {
        ResponseWrapperUser allUsers = userService.getUsers();
        if (allUsers.getCount() == 0) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(allUsers);
    }

    @PatchMapping("/me")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDto) {
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserDto user = userService.updateUser(userDto);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping("/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok(newPassword);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id) {
        if (id < 0) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        UserDto userDto = userService.getUser(id);
        if (userDto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(userDto);
    }
}
