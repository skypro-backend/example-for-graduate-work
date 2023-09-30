package ru.skypro.homework.controller;

import org.springframework.web.bind.annotation.*;
import ru.skypro.homework.dto.UpdateUser;

@RestController
@CrossOrigin(value = "http://localhost:3000")
public class updateUserController {
    @PostMapping("/updateUser")
    public UpdateUser updateUser(@RequestBody UpdateUser user) {
        return new UpdateUser();
    }

}
