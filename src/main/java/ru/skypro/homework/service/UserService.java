package ru.skypro.homework.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import ru.skypro.homework.dto.NewPassword;
import ru.skypro.homework.dto.UpdateUser;
import ru.skypro.homework.dto.User;
import ru.skypro.homework.entity.UserEntity;

import java.util.Optional;

public interface UserService {

    ResponseEntity<String> setPassword(NewPassword newPassword, UserDetails userDetails);

    ResponseEntity<User> getUser(UserDetails userDetails);

    ResponseEntity<UpdateUser> updateUser(UpdateUser updateUser, UserDetails userDetails);
}
