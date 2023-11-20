package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;

public interface AddService {
    boolean login(String userName, String password);

    boolean register(Register register);
}
