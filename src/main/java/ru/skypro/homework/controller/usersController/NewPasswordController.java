package ru.skypro.homework.controller.usersController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.homework.dto.usersDTO.NewPasswordDTO;

@RestController
@RequestMapping("/users")
public class NewPasswordController {
    @PutMapping

    public NewPasswordDTO editPassword(@RequestBody NewPasswordDTO newPasswordDTO){

        return NewPasswordService.editNewPassword(newPasswordDTO);
        }
    }

