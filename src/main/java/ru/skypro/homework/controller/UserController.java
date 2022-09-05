package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("users")
public class UserController {

    @PostMapping
    public ResponseEntity<?> addUser(/*@RequestBody User user*/) {
        System.out.println("addUser");
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<?> getUsers() {
        System.out.println("getUsers");
        return ResponseEntity.ok().build();
    }

    @PatchMapping("me")
    public ResponseEntity<?> updateUser(/*@RequestBody User user*/) {
        System.out.println("updateUser");
        return ResponseEntity.ok().build();
    }

    @PostMapping("set_password")
    public ResponseEntity<?> setPassword(/*@RequestBody Password newPassword*/) {
        System.out.println("setPassword");
        return ResponseEntity.ok().build();
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getUser(@PathVariable Integer id) {
        System.out.println("geteUser");
        return ResponseEntity.ok().build();
    }



}
