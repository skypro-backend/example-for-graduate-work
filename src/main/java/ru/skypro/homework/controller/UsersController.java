package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.homework.dto.CustomUserDetails;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;
import ru.skypro.homework.mapper.UserMapper;
import ru.skypro.homework.repository.UserEntityRepository;
import ru.skypro.homework.service.impl.UsersServiceImpl;

@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UsersController {
    private  UserMapper mapper;
    private UserEntityRepository userEntityRepository;
    private UsersServiceImpl usersService;

    public UsersController(UserMapper mapper, UserEntityRepository userEntityRepository, UsersServiceImpl usersService) {
        this.mapper = mapper;
        this.userEntityRepository = userEntityRepository;
        this.usersService = usersService;
    }

    @PostMapping("/set_password")
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword, CustomUserDetails userDetails)
    {
        usersService.setPassword(newPassword, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/me")
    public ResponseEntity<User> getUser(@AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(usersService.getUser(userDetails),HttpStatus.OK);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser, @AuthenticationPrincipal CustomUserDetails userDetails) {
        return new ResponseEntity<>(usersService.updateUser(updateUser, userDetails), HttpStatus.OK);
    }
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage( @RequestParam MultipartFile image, @AuthenticationPrincipal CustomUserDetails userDetails) {
        usersService.updateUserImage(image, userDetails);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
