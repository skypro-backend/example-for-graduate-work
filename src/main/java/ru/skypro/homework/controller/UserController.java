package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.ImageDTO;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.UserDTO;
import ru.skypro.homework.mapper.ImageMapper;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;
import ru.skypro.homework.repository.UserRepository;
import ru.skypro.homework.service.ImageService;
import ru.skypro.homework.service.UserService;

import java.io.IOException;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * Getting information about an authorized user
     * <br>
     * Using {@link UserService#findUser(Authentication)}
     * @param authentication
     * @return UserDTO
     */
    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> getUser(Authentication authentication) {
        return ResponseEntity.ok(userService.findUser(authentication));
    }

    /**
     * Updating authorized user information
     * <br>
     * Using {@link UserService#editUser(UpdateUser, Authentication)}
     * @param updateUserDto
     * @param authentication
     * @return UpdateUser
     */
    @PatchMapping("/users/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUserDto, Authentication authentication) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userService.editUser(updateUserDto, authentication));
    }

    /**
     * Updating an authorized user's avatar
     * <br>
     * Using {@link Authentication#getName()},
     * {@link UserService#editUserImage(MultipartFile, Authentication)}
     * @param image
     * @param authentication
     * @return String
     * @throws IOException
     */
    @PatchMapping(value = "/users/me/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> updateUserImage(@RequestPart("image") MultipartFile image,
                                                  Authentication authentication) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(userService.editUserImage(image, authentication));
    }

    /**
     * Password update
     * using {@link UserService#setPassword(NewPassword, Authentication)}
     * @param newPassword
     * @param authentication
     * @return NewPassword
     */
    @PostMapping("/users/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPassword,
                                                   Authentication authentication) {
        HttpHeaders headers = new HttpHeaders();
        if (userService.setPassword(newPassword, authentication)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
