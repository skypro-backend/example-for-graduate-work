package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.CreateUser;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.ResponseWrapper;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.AuthService;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    @PostMapping
    public ResponseEntity<CreateUser> addUser(@RequestBody CreateUser user) {
        System.out.println("addUser");
        return ResponseEntity.ok().build();
    }

    @GetMapping("me")
    public ResponseEntity<ResponseWrapper<User>> getUsers() {
        System.out.println("getUsers");

        User user = new User();
        user.setId(1);
        user.setFirstName("first");

        User user2 = new User();
        user2.setId(2);
        user2.setFirstName("second");

        return ResponseEntity.ok(new ResponseWrapper(user, user2));
    }

    @PatchMapping("me")
    public ResponseEntity<User> updateUser(@RequestBody User user) {
        System.out.println("updateUser");
        return ResponseEntity.ok(user);
    }

    @PostMapping("set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword) {
        System.out.println("setPassword");
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        System.out.println("getUser");
        return ResponseEntity.ok(new User());
    }



}
