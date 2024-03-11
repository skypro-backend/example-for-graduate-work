package ru.skypro.homework.controller;

import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.service.impl.UserServiceImpl;

/**
 * Controller handles user requests
 */
@RestController
@RequestMapping("/users")
@CrossOrigin("http://localhost:3000/")
public class UserController {

    private final UserServiceImpl userService;

    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    /**
     * Sets new password
     * @param newPassword is NewPassword DTO consisting of current password and new password
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Http status
     */
    @PostMapping("/set_password")
    public ResponseEntity<?> setPassword(@RequestBody NewPassword newPassword,
                                         @Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        userService.setPassword(newPassword, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Gets info about a user
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return ResponseEntity<User> consisting of User DTO(user id, email, firstName, lastName, phone, role, image) and Http status
     */
    @GetMapping("/me")
    public ResponseEntity<User> getUser(@Parameter(hidden = true) @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(userService.getUser(userDetails), HttpStatus.OK);
    }

    /**
     * Updates info about a user
     * @param updateUser is UpdateUser DTO consisting of firstname, lastname and phone
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return ResponseEntity<UpdateUser> consisting of UpdateUser DTO(firstname, lastname and phone) and Http status
     */
    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser,
                                                 @AuthenticationPrincipal UserDetails userDetails) {
        return new ResponseEntity<>(userService.updateUser(updateUser, userDetails), HttpStatus.OK);
    }

    /**
     * Updates an image of a user
     * @param image of a user
     * @param userDetails contains details such as the user's username, password, authorities (roles), and additional attributes
     * @return Http status
     */
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateImage(@RequestParam("image") MultipartFile image,
                                         @AuthenticationPrincipal UserDetails userDetails) {
        userService.updateImage(image, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}