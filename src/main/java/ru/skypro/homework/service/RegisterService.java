package ru.skypro.homework.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import ru.skypro.homework.dto.Register;

public interface RegisterService {
    ResponseEntity<String> registerUser(Register register);
}
