package ru.skypro.homework.service;

import ru.skypro.homework.dto.CreateOrUpdateAd;
import ru.skypro.homework.model.Ad;
import ru.skypro.homework.model.Image;
import ru.skypro.homework.model.User;

public interface AdService {

    boolean createAd(CreateOrUpdateAd createOrUpdateAd, User user, Image image);
    Ad updateAd(Ad ad, CreateOrUpdateAd createOrUpdateAd);

}
