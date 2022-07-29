package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.User;

import java.util.Collection;

@RestController
@CrossOrigin(value = "http://localhost:3000")
@RequestMapping("/users")
public class UserController {
    @PostMapping
    public ResponseEntity<User>addUser(@RequestBody User user){
        return ResponseEntity.ok(user);
    }
    @PatchMapping("/me")
    public ResponseEntity<User>updateUser(@RequestBody User user){
        return ResponseEntity.ok(user);
    }
    @GetMapping("/me")
    public ResponseEntity<Collection<User>> getUsers(){
        return ResponseEntity.ok(null);
    }
    @PostMapping("/set_password")
    public ResponseEntity<NewPassword>setPassword(@RequestBody NewPassword newPassword) {
        return ResponseEntity.ok(newPassword);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User>getUser(@PathVariable("id") int id){
        return ResponseEntity.ok(new User());
    }
}
