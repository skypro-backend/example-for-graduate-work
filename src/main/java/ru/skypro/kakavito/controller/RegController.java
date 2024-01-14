package ru.skypro.kakavito.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.kakavito.dto.Register;
import ru.skypro.kakavito.service.RegService;

/**
 * Класс для управления потоком данных при регистрации
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
public class RegController {

    private final RegService regService;

    /**
     * Запрос на регистрацию и создание нового пользователя
     *
     * @param register
     * @see ru.skypro.kakavito.model.User
     */
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Register register) {
        if (regService.register(register)) {
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
