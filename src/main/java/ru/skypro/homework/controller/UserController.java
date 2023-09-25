package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.model_dto.NewPasswordDto;
import ru.skypro.homework.dto.model_dto.UserDto;
import ru.skypro.homework.model.User;
import ru.skypro.homework.service.UserService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

      private final UserService userService;

      @PostMapping
      public UserDto updatePassword(@RequestBody NewPasswordDto newPassword) {
            return new UserDto ();
      }

      @GetMapping
      public ResponseEntity<User> findUserById (@PathVariable("id") int userId) {
            return ResponseEntity.ok(userService.findUserById(userId));
      }
      @GetMapping
      public ResponseEntity<User> getUsers (@PathVariable String email) {
            User userFound = userService.getUsers (email);
            if (userFound == null) {
                  return ResponseEntity.notFound ().build ();
            }
            return ResponseEntity.ok (userFound);
      }

      @GetMapping("/allUsers")
      public ResponseEntity<List <User>> allUsers() {
            return ResponseEntity.ok(userService.allUsers ());
      }

      @PutMapping
      public ResponseEntity<User> addUser (@RequestBody User user) {
            User newUser = userService.addUser (user);
            return ResponseEntity.ok().body(newUser);
      }

      @DeleteMapping("/{userId}")
      public void deleteUser (@PathVariable("id") int userId) {
            userService.deleteUser (userId);
      }

}
