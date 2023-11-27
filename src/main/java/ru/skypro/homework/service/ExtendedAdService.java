package ru.skypro.homework.service;

import ru.skypro.homework.dto.Register;
import ru.skypro.homework.dto.ExtendedAd;

public interface ExtendedAdService {
    ExtendedAd get(Integer id, boolean extendedAd);

    boolean UpdateAd(Integer id, boolean extendedAd);

    boolean UpdateAd(boolean extendedAd, Integer id);

    boolean ExtendedAd(Integer id, boolean add);

    boolean ExtendedAd(boolean add, Integer id);

    ExtendedAd update(Integer id, boolean extendedAd);

    boolean login(String userName, String password);

    boolean register(Register register);
}


