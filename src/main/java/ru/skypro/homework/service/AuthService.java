package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

import java.util.Optional;

public interface AuthService {

    boolean login(String username, String password);

    Optional<Integer> register(Register register);
}
