package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import ru.skypro.homework.dto.RegisterDto;

public interface RegisterService {
    boolean registerUser(RegisterDto register);
}
