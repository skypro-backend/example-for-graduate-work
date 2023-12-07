package ru.skypro.homework.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
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
    public ResponseEntity<Void> setPassword(@RequestBody NewPassword newPassword)
    {
        usersService.setPassword(newPassword);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/me")
    public ResponseEntity<User> getUser() {
        return new ResponseEntity<>(usersService.getUser(),HttpStatus.OK);
    }

    @PatchMapping("/me")
    public ResponseEntity<UpdateUser> updateUser(@RequestBody UpdateUser updateUser) {
        return new ResponseEntity<>(usersService.updateUser(updateUser), HttpStatus.OK);
    }
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Void> updateUserImage( @RequestParam MultipartFile image) {
        usersService.updateUserImage(image);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
