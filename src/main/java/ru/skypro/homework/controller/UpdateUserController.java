package ru.skypro.homework.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.service.UserService;

@RestController
@RequestMapping("http://localhost:8080/updateUser")
public class UpdateUserController {
   private final UserService userService;

    public UpdateUserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<UpdateUser> setInformationUser(@RequestBody UpdateUser updateUser){
        return ResponseEntity.ok(userService.setInfoUser(updateUser));
    }
}
