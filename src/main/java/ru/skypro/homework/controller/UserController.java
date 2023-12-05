package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ImageService imageService;
    private final ImageMapper imageMapper;

    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> get(Authentication authentication) {
        String username = authentication.getName();
        UserDTO userDTO = userMapper.mapToDTO(userRepository.findByUsername(username));
        return ResponseEntity.ok(userDTO);
    }

    @PatchMapping("/users/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUserDto, Authentication authentication) {
        String username = authentication.getName();
        HttpHeaders headers = new HttpHeaders();
        userMapper.saveFromUpdate(username, updateUserDto);
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(updateUserDto);
    }

    @PatchMapping(value = "/users/me/image", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<String> updateUserImage(@RequestPart("image") MultipartFile image,
                                                  Authentication authentication) throws IOException {
        String username = authentication.getName();
        User user = userRepository.findByUsername(username);
        Image imageToDB = imageService.uploadImage(image);
        user.setImage(imageToDB);
        userRepository.save(user);
        ImageDTO imageDTO = imageMapper.mapToDTO(imageToDB);
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok(imageDTO.getUrl());
    }
    @PutMapping("/users/set_password")
    public ResponseEntity<NewPassword> setPassword(@RequestBody NewPassword newPasswordDto) {
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(new NewPassword());
    }

}
