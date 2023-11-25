package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ad;
import ru.skypro.homework.dto.Register;

public interface AdService {
    Ad get(Integer id, boolean add);

    boolean AdDto(Integer id, boolean add);

    Ad update(Integer id, boolean add);

    boolean login(String userName, String password);

    boolean register(Register register);
}
