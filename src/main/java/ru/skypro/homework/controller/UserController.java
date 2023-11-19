package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;
    private Long id;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{me}")
    public User get(@PathVariable long id) {
        return userService.get(id);
    }

    @PutMapping("/{me}")
    public ResponseEntity<User> update() {
        return update(null);
    }

    @PutMapping("/{me}")
    public ResponseEntity<User> update(@PathVariable Long id) {
        ResponseEntity<User> result;
        this.id = id;
        User savedUser = userService.update(id, user);
        if (savedUser == null) {
            result = ResponseEntity.badRequest().build();
        } else {
            result = ResponseEntity.ok(savedUser);
        }
        return result;
    }
    @PutMapping("/{set_password}")
    public ResponseEntity<User> update(@PathVariable Long set_password, @RequestBody User user) {
        User savedUser = userService.update(set_password, user);
        if (savedUser == null) {
            return ResponseEntity.badRequest().build();
        } else {
            return ResponseEntity.ok(savedUser);
        }
    }
//    set_password
}
