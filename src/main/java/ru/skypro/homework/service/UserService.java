package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.NewPassword;

public interface UserService {
    ResponseEntity<?> setPassword(NewPassword newPassword);

    ResponseEntity<?> getUser();
}
