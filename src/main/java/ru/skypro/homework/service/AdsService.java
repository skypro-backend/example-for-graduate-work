package ru.skypro.homework.service;

import ru.skypro.homework.dto.Ads;
import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.UpdateAd;

public interface AdsService {
    Ads get(Integer id, boolean ads);

    boolean Ads(Integer id, boolean ads);

    boolean Ads(boolean ads, Integer id);

    Ads update(Integer id, boolean ads);

    boolean login(String userName, String password);

    boolean register(Register register);
}

