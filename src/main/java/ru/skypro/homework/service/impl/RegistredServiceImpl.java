package ru.skypro.homework.service.impl;

import lombok.AllArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.homework.dto.Registred;
import ru.skypro.homework.repository.RergistredRepository;

@Service
@AllArgsConstructor
public class RegistredServiceImpl {
    private final RergistredRepository repository;

    public void create(Registred register) {
        try {
            repository.create(register);
        } catch (ResponseStatusException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST);
        }
    }
}
