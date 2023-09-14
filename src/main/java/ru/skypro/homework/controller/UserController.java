package ru.skypro.homework.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.user.ChangePasswordRequest;
import ru.skypro.homework.dto.user.UpdateUserProfileRequest;
import ru.skypro.homework.entity.Register;
import ru.skypro.homework.dto.user.UpdateUserRequest;
import ru.skypro.homework.dto.user.UserDTO;
import ru.skypro.homework.service.impl.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public List<UserDTO> getAllUsers() {
        try {
            return userService.getAllUsers();
        } catch (Exception e) {
            logger.error("An error occurred while fetching all users", e);
            throw e; // Rethrow the exception to let Spring handle it
        }
    }

    @GetMapping("/{userId}")
    public UserDTO getUserById(@PathVariable Long userId) {
        try {
            return userService.getUserById(userId);
        } catch (Exception e) {
            logger.error("An error occurred while fetching user with ID: " + userId, e);
            throw e;
        }
    }

    @PostMapping("/")
    public UserDTO createUser(@RequestBody Register register) {
        try {
            return userService.createUser(register);
        } catch (Exception e) {
            logger.error("An error occurred while creating a user", e);
            throw e;
        }
    }

    @PutMapping("/{userId}")
    public UserDTO updateUser(@PathVariable Long userId, @RequestBody UpdateUserRequest updateUserRequest) {
        try {
            return userService.updateUser(userId, updateUserRequest);
        } catch (Exception e) {
            logger.error("An error occurred while updating user with ID: " + userId, e);
            throw e;
        }
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (Exception e) {
            logger.error("An error occurred while deleting user with ID: " + userId, e);
            throw e;
        }
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<UserDTO> getUserProfile(@PathVariable Long userId) {
        UserDTO userProfile = userService.getUserProfile(userId);
        return ResponseEntity.ok(userProfile);
    }

    @PutMapping("/users/{userId}")
    public ResponseEntity<UserDTO> updateUserProfile(
            @PathVariable Long userId,
            @RequestBody UpdateUserProfileRequest updateUserProfileRequest) {
        UserDTO updatedUserProfile = userService.updateUserProfile(userId, updateUserProfileRequest);
        return ResponseEntity.ok(updatedUserProfile);
    }

    @PutMapping("/users/{userId}/change-password")
    public ResponseEntity<?> changePassword(
            @PathVariable Long userId,
            @RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(userId, changePasswordRequest);
        return ResponseEntity.ok().build();
    }
}