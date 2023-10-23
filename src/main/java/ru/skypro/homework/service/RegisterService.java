package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.RegisterDto;

public interface RegisterService {
    ResponseEntity<String> registerUser(RegisterDto register);
}
