package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateAd;

public interface UpdateAdService {
        UpdateAd get(Integer id, boolean updateAdd);

        boolean UpdateAd(Integer id, boolean updateAdd);

        boolean UpdateAd(boolean add, Integer id);

        UpdateAd update(Integer id, boolean updateAdd);

        boolean login(String userName, String password);

        boolean register(Register register);
    }

